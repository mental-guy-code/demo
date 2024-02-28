<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)" v-if="tableData.length">
        <el-table
                :data="tableData"
                stripe
                size="large"
                highlight-current-row
                :show-header="false"
        >
            <el-table-column label="User" align="center" width="80px">
                <template #default="scope" >
                    <div style="position: absolute; top: 2px">
                        <!-- 第一列内容在第一行 -->
                        <el-avatar :src="'/api/files/'+scope.row.author+'.jpg'">
                            <user-filled />
                        </el-avatar>
                        <!-- 第二列内容在第二行 -->
                        <p>{{ scope.row.author }}</p>
                    </div>

                </template>
            </el-table-column>
            <el-table-column label="Title" >
                <template #default="scope">
                    <!-- 第一列内容在第一行 -->
                    <h2>{{ scope.row.title }}</h2>
                    <!-- 第二列内容在第二行 -->
                    <div v-html="scope.row.content"></div>
<!--                    <p>{{ scope.row.content }}</p>-->
                </template>
            </el-table-column>
            <el-table-column prop="time" width="170px"></el-table-column>
            <el-table-column fixed="right" label="Operations" width="149px">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)">Edit</el-button>
                    <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
                        <template #reference>
                            <el-button size="small">Delete</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="load"
                    @current-change="load"
            >
            </el-pagination>
        </div>
        <el-dialog v-model="dialogFormVisible" :title="title">
            <el-form :model="form" >
                <el-form-item label="Title">
                    <el-input v-model="form.title" autocomplete="off"/>
                </el-form-item>
                <div style="border: 1px solid #ccc">
                    <Toolbar
                            style="border-bottom: 1px solid #ccc"
                            :editor="editorDialog"
                            :defaultConfig="toolbarConfig"
                            :mode="mode"
                    />
                    <Editor
                            style="height: 500px; overflow-y: hidden;"
                            v-model="form.content"
                            :defaultConfig="editorConfig"
                            :mode="mode"
                            @onCreated="handleCreatedDialog"
                    />
                </div>
            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel">Cancel</el-button>
        <el-button type="primary" @click="save">
          Confirm
        </el-button>
      </span>
            </template>
        </el-dialog>

        <div style="border: 1px solid #ccc; margin: 50px">
            <div style="margin: 10px">
                Title
                <el-input v-model="valueTitle" autocomplete="off"/>
            </div>

            <Toolbar
                    style="border-bottom: 1px solid #ccc"
                    :editor="editorMain"
                    :defaultConfig="toolbarConfig"
                    :mode="mode"
            />
            <Editor
                    style="height: 500px; overflow-y: hidden;"
                    v-model="valueHtml"
                    :defaultConfig="editorConfig"
                    :mode="mode"
                    @onCreated="handleCreatedMain"
            />
        </div>
        <div style="text-align: center">
            <el-button type="primary" size="large" @click="publish">Publish</el-button>
        </div>
    </div>
</template>

<script>
    import {Search, Plus, UserFilled} from '@element-plus/icons-vue';
    import request from "../utils/request";
    import { ElMessage } from 'element-plus';

    import '@wangeditor/editor/dist/css/style.css' // 引入 css

    import { Editor, Toolbar } from '@wangeditor/editor-for-vue'


    export default {
        name: "UserManage",
        components: {
            Search, Plus, ElMessage, UserFilled, Editor, Toolbar
        },
        data() {
            return {
                form: {},
                title: "新增",
                dialogFormVisible: false,
                search_snp: "",
                total: 0,
                currentPage: 1,
                pageSize: 10,
                tableData: [],
                editorDialog:null,
                editorMain: null,
                mode: 'default', // 或 'simple'
                toolbarConfig: {},
                editorConfig: { placeholder: '请输入内容...' },
                valueTitle:"",
                valueHtml: "",
            }
        },
        created() {
            this.load()
        },

        // mounted() {
        //     setTimeout(() => {
        //         this.valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
        //     }, 1500)
        // },
        beforeDestroy() {
            const editorMain = this.editorMain
            const editorDialog = this.editorDialog

            if (editorMain != null) {
                editorMain.destroy() // 组件销毁时，及时销毁编辑器
                }
            if (editorDialog != null) {
                editorDialog.destroy() // 组件销毁时，及时销毁编辑器
            }
        },
        methods: {
            // sortDate(){
            //     this.$nextTick(() => {
            //         this.defaultSort = { prop: 'time', order: 'descending' };
            //     })
            // },
            load() {
                request.get("/blog", {
                    params: { //params
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                })
            },

            // handleSizeChange() {   //改变每页数量
            //     this.load()
            // },
            // handleCurrentChange() {  //改变当前所在页
            //     this.load()
            // },
            handleAdd() {
                this.form = {};
                this.title="新增";
                this.dialogFormVisible = true;
            },
            handleEdit(row) {
                this.title = "编辑";
                this.form = JSON.parse(JSON.stringify(row));
                this.dialogFormVisible = true;
            },
            handleDelete(id) {
                request.delete("/blog", {
                    params: { //params
                        id: id,
                    }
                }).then(res => {
                    if (res.code === 200) {
                        this.$message.success(res.message)
                    }
                    this.load()
                })
                // request.delete("/user/" + id).then(res => {})
                // 传参数
            },
            save() {
                // const now = new Date();
                //
                // // 设置时区为东八区
                // now.setHours(now.getHours());
                //
                // const year = now.getFullYear();
                // const month = (now.getMonth() + 1).toString().padStart(2, '0');
                // const day = now.getDate().toString().padStart(2, '0');
                // const hours = now.getHours().toString().padStart(2, '0');
                // const minutes = now.getMinutes().toString().padStart(2, '0');
                // const seconds = now.getSeconds().toString().padStart(2, '0');
                //
                // this.form.time = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
                if (this.form.id){ //更新
                    request.put("/blog", this.form).then(res => {
                        if (res.code === 200) {
                            this.$message.success(res.message)
                        } else if (res.code === 400){
                            this.$message.info(res.message)
                        }
                    });
                } else {
                    request.post("/blog", this.form).then(res => {

                    });
                }
                this.load()
                this.dialogFormVisible = false;
            },
            cancel() {
                this.dialogFormVisible = false;
                this.form={};
            },
            beforeAvatarUpload(rawFile){
                if (rawFile.type !== 'image/jpeg') {
                    ElMessage.error('Avatar picture must be JPG format!')
                    return false
                } else if (rawFile.size / 1024 / 1024 > 2) {
                    ElMessage.error('Avatar picture size can not exceed 2MB!')
                    return false
                }
                return true
            },
            handleAvatarSuccess(res){
                let lastGeneratedLetter = '';
                let randomLetter;
                do {
                    randomLetter = String.fromCharCode(97 + Math.floor(Math.random() * 26)); // 小写字母
                } while (randomLetter === lastGeneratedLetter);
                lastGeneratedLetter = randomLetter;
                this.form.cover = res.data+`?randomLetter=${randomLetter}`;
                // this.$forceUpdate();
            },
            handleCreatedMain(editor) {
                this.editorMain = Object.seal(editor); // 记录 editor 实例，重要！
            },
            handleCreatedDialog(editor) {
                this.editorDialog = Object.seal(editor); // 记录 editor 实例，重要！
            },
            publish(){
                this.form = {};
                this.form.title = this.valueTitle;
                this.form.content = this.valueHtml;
                this.form.author = JSON.parse(sessionStorage.getItem('userData')).id;
                // const now = new Date();
                //
                // // 设置时区为东八区
                // now.setHours(now.getHours());
                //
                // const year = now.getFullYear();
                // const month = (now.getMonth() + 1).toString().padStart(2, '0');
                // const day = now.getDate().toString().padStart(2, '0');
                // const hours = now.getHours().toString().padStart(2, '0');
                // const minutes = now.getMinutes().toString().padStart(2, '0');
                // const seconds = now.getSeconds().toString().padStart(2, '0');
                //
                // this.form.time = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
                request.post("/blog", this.form).then(res => {
                    if (res.code === 200) {
                        this.$message.success(res.message);
                        this.form ={};
                        this.load();
                        this.editorMain.clear();
                        this.valueTitle = "";
                    } else if (res.code === 400){
                        this.$message.info(res.message);
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .avatar {
        width: 200px;
        height: 200px;
    }
</style>

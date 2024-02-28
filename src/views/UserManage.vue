<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)">
        <div style="margin: 10px 10px;">
            <el-button type="primary" @click="handleAdd">新增</el-button>
<!--            <el-button type="primary">导入</el-button>-->
<!--            <el-button type="primary">导出</el-button>-->
        </div>
        <div style="margin: 10px 10px; width: 30%; display: flex; background-color: cornsilk">
            <el-input
                    v-model="search_snp"
                    class="w-50 m-2"
                    placeholder="Type something"
                    clearable
                    @keyup.enter="load"
            >
                <template #prefix>
                    <el-icon class="el-input__icon">
                        <search/>
                    </el-icon>
                </template>
            </el-input>
            <el-button type="primary" style="margin-left: 5px" @click="load">
                <el-icon class="el-input__icon">
                    <search/>
                </el-icon>
                Search
            </el-button>
        </div>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="id" label="ID" sortable width="100"></el-table-column>
            <el-table-column prop="username" label="UserName" width="100"></el-table-column>
            <el-table-column prop="nickname" label="NickName" width="100"></el-table-column>
            <el-table-column prop="age" label="Age" width="100"></el-table-column>
            <el-table-column prop="sex" label="Sex" width="100"></el-table-column>
            <el-table-column prop="address" label="Address" width="100"></el-table-column>
            <el-table-column prop="cover" label="Avatar" width="100">
                <template #default="scope">
                    <el-image
                            v-if="scope.row.cover"
                            style="height: 100px; width: 100px"
                            :src="'/api/files/'+scope.row.cover"
                            :preview-src-list="['/api/files/'+scope.row.cover]"
                            :preview-teleported="true"
                    >
                    <!--  :preview-teleported="true"  使图片不穿模
                     作用：image-viewer 是否插入至 body 元素上。 嵌套的父元素属性会发生修改时应该将此属性设置为 true-->
                    </el-image>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="Operations">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
                    >Edit
                    </el-button
                    >
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
                <el-form-item label="ID" v-show="idVisible">
                    <el-input v-model="form.id" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="UserName">
                    <el-input v-model="form.username" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="PassWord">
                    <el-input v-model="form.password" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="Age">
                    <el-input v-model="form.age" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="Sex">
                    <el-radio v-model="form.sex" label="male">Male</el-radio>
                    <el-radio v-model="form.sex" label="female">Female</el-radio>
                </el-form-item>
                <el-form-item label="NickName">
                    <el-input v-model="form.nickname" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="Zones">
                    <el-select v-model="form.address" placeholder="Please select a zone">
                        <el-option label="Shanghai" value="shanghai"/>
                        <el-option label="Beijing" value="beijing"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="Avatar" v-show="uploadVisible">
                    <el-upload
                            class="avatar-uploader"
                            action="/api/files/upload"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload"
                            :data="{userID : form.id}"
                            :show-file-list="false"
                    >
                        <el-image v-if="form.cover" :src="'/api/files/'+form.cover" class="avatar"/>
                        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                        <template #tip>
                            <div class="el-upload__tip">
                                jpg files with a size less than 2MB.
                            </div>
                        </template>
                    </el-upload>
                </el-form-item>
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
    </div>
</template>

<script>
    import {Search, Plus} from '@element-plus/icons-vue';
    import request from "../utils/request";
    import { ElMessage } from 'element-plus';

    export default {
        name: "UserManage",
        components: {
            Search, Plus, ElMessage
        },
        data() {
            return {
                form: {},
                title: "新增",
                dialogFormVisible: false,
                idVisible:false,
                uploadVisible:true,
                search_snp: "",
                total: 0,
                currentPage: 1,
                pageSize: 10,
                tableData: [],
            }
        },
        created() {
            this.load()
        },
        methods: {
            load() {
                request.get("/user", {
                    params: { //params
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData = res.data.records
                    this.total = res.data.total
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
                this.uploadVisible=false;
                this.idVisible = false;
                this.title="新增";
                this.dialogFormVisible = true;
            },
            handleEdit(row) {
                this.title = "编辑";
                this.form = JSON.parse(JSON.stringify(row));
                this.uploadVisible=true;
                this.dialogFormVisible = true;
                this.idVisible = true;
            },
            handleDelete(id) {
                request.delete("/user", {
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
                if (this.form.id){ //更新
                    request.put("/user", this.form).then(res => {
                        if (res.code === 200) {
                            this.$message.success(res.message)
                        } else if (res.code === 400){
                            this.$message.info(res.message)
                        }
                    });
                } else {
                    request.post("/user", this.form).then(res => {

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

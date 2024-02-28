<template>
    <div class="nav">
        <div style="padding-left: 30px;font-weight: bold;color: deepskyblue;">
            Cotton DNA Methylation Database
        </div>
        <div style="flex: 1"></div>
        <div style="width: 100px; display: flex; align-items: center">
            <el-dropdown>
    <span class="el-dropdown-link" v-if="form">
      {{form.username}}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人信息</el-dropdown-item>
                        <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>


<script>
    import {ArrowDown} from '@element-plus/icons-vue'

    export default {
        name: "Header",
        components:{
            ArrowDown
        },
        methods:{
            logout(){
                sessionStorage.clear();
                this.$router.push('/login')
            }
        },
        beforeCreate() {
            //const id = this.$store.state.userData.id;
            // if (id == null){
            //     this.$router.push("/login")
            // }
            // 如果没登录返回登录界面的写法
            // if (!sessionStorage.getItem('userData')){
            //     this.$router.push("/login")
            // }
        },
        data(){
            return{
                form: JSON.parse(sessionStorage.getItem('userData'))
                //     {
                //     id: JSON.parse(sessionStorage.getItem('userData')).id,
                //     username: JSON.parse(sessionStorage.getItem('userData')).username,
                // }
            }
        },
        computed: {
            // id(){
            //     return this.$route.params.id;
            // },
            // username(){
            //     return this.$route.params.username;
            // }
        }
    }
</script>

<style scoped>
    .nav {
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #ccc;
        display: flex;
        background-color: white;
    }
</style>

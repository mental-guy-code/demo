<template>
    <div class="loginBackground">
        <div class="loginWindows">
            <div style="text-align: center; padding: 30px 0">
                欢迎注册
            </div>
            <el-form :model="form" label-width="100px" style="margin: auto" >
                <el-form-item label="User Name" >
                    <el-input v-model="form.username" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input v-model="form.password" style="width: 200px;" show-password/>
                </el-form-item>
                <el-form-item label="Confirm">
                    <el-input v-model="confirm" style="width: 200px;" show-password/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="register">注册</el-button>
                    <el-button type="primary" @click="$router.push('/login')">返回登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import request from "../utils/request";
    import { ElMessageBox } from 'element-plus'

    export default {
        name: "Register",
        components:{

        },
        data(){
            return{
                form: {},
                confirm: "",

            }
        },
        methods: {
            register(){
                if (this.form.password === this.confirm){
                    request.post("/user", this.form).then(res =>{
                        if (res.code ===200){
                            this.$message.success(res.message);
                            this.form.id = res.data.id;
                            this.open()
                            //this.$router.push("/login");
                        } else {
                            this.$message.error(res.message);
                        }
                    })
                }

            },
            open(){
                ElMessageBox.alert('请保存你的ID： '+this.form.id, '确认你的ID', {
                    confirmButtonText: 'OK',
                    callback: () => {this.$router.push("/login")}
                })
            }

        }
    }
</script>

<style scoped>
    .loginBackground {
        width: 100%;
        height: 100vh;
        background-color: cornflowerblue;
        overflow: hidden;
    }
    .loginWindows {
        background-color: cornsilk;
        position: absolute;
        top: 0;
        bottom: 50px;
        left: 0;
        right: 0;
        margin: auto;
        width: 400px;
        height: 280px;
        border-radius: 10px;
    }
    input {
        width: 100px;
    }
</style>

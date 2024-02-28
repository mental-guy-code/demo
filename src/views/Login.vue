<template>
    <div class="loginBackground">
        <div class="loginWindows">
            <div style="text-align: center; padding: 30px 0">
                欢迎登陆
            </div>
            <el-form :model="form" label-width="100px" style="margin: auto">
                <el-form-item label="User ID" >
                    <el-input v-model="form.username" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input v-model="form.password" style="width: 200px;" show-password/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="primary" @click="$router.push('/register')">注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import request from "../utils/request";

    export default {
        name: "Login",
        data(){
            return{
                form: {},

            }
        },
        methods: {
            login(){
                //console.log(this.form)
                request.post("/login", this.form, {
                    transformRequest: [
                        function (data) {
                            let ret = ''
                            for (let it in data) {
                                ret +=
                                    encodeURIComponent(it) +
                                    '=' +
                                    encodeURIComponent(data[it]) +
                                    '&'
                            }
                            return ret
                        }
                    ],
                }).then(res =>{
                    if (res.code ===200){
                        this.$message.success(res.message)
                        console.log(JSON.stringify(res))
                        // this.$store.commit('setUserData', this.form);
                        sessionStorage.setItem('userData', JSON.stringify(this.form));
                        sessionStorage.setItem('userToken', res.token);
                        this.$router.push("/home")
                    } else {
                        this.$message.error(res.message)
                    }
                })
            },

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

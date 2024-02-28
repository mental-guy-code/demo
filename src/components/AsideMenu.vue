<template>
    <div class="aside-menu">
    <el-menu
            :default-active="this.$route.path.substr(1,)"
            class="el-menu-vertical-demo"
            style="min-height: calc(100vh - 50px);"
            router
    >
        <el-menu-item index="home" >
            <el-icon>
                <home-filled />
            </el-icon>
            <span>Home</span>
        </el-menu-item>
        <el-menu-item index="userManage" >
            <el-icon>
                <search />
            </el-icon>
            <span>Users</span>
        </el-menu-item>
        <el-menu-item index="treeHole" >
            <el-icon>
                <search />
            </el-icon>
            <span>Tree Hole</span>
        </el-menu-item>
        <el-menu-item index="search" >
            <el-icon>
                <search />
            </el-icon>
            <span>Search</span>
        </el-menu-item>
        <el-sub-menu index="browse">
            <template #title>
                <el-icon>
                    <icon-menu/>
                </el-icon>
                <span>Browse</span>
            </template>
            <el-menu-item index="overview" >Overview</el-menu-item>
            <el-menu-item index="gwas" >GWAS</el-menu-item>
            <el-menu-item index="eqtl" >eQTL</el-menu-item>
            <el-menu-item index="meqtl" >meQTL</el-menu-item>
            <el-menu-item index="emqtl" >emQTL</el-menu-item>
            <el-menu-item index="ewas" >EWAS</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="test1" >
            <el-icon>
                <download />
            </el-icon>
            <span>Test1</span>
        </el-menu-item>
        <el-menu-item index="test2" >
            <el-icon>
                <question-filled />
            </el-icon>
            <span>Test2</span>
        </el-menu-item>
    </el-menu>
    </div>
</template>

<script>
    import {
        HomeFilled,
        Menu as IconMenu,
        Search,
        Download,
        QuestionFilled,

    } from '@element-plus/icons-vue'

    export default {
        name: "AsideMenu",
        components:{
            HomeFilled,
            IconMenu,
            Search,
            Download,
            QuestionFilled,

        },
        methods:{
            // saveNavState(activePath){
            //     sessionStorage.setItem('activePath', activePath)
            //     this.activePath = sessionStorage.getItem('activePath')
            // }
        },
        data() {
            return {
                // activePath: this.$route.path.substr(1,)
            }
        },
        // created() {
        //     console.log(this.$route.path.substr(1,))
        // }
        // computed: {
        //     // 我们使用计算属性来获取到当前点击的菜单的路由路径，然后设置default-active中的值
        //     // 使得菜单在载入时就能对应高亮
        //     activeMenu() {
        //         const route = this.$route
        //         const { meta, path } = route
        //         // if set path, the sidebar will highlight the path you set
        //         // 可以在路由配置文件中设置自定义的路由路径到meta.activeMenu属性中，来控制菜单自定义高亮显示
        //         return path
        //     }
        // }

    }


</script>

<style scoped>
    .aside-menu{
        width: 150px;
    }

    .el-sub-menu .el-menu-item {
        min-width: 150px;
    }

</style>

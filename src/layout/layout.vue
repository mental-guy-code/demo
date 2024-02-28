<template>
<div>
    <el-affix>
        <Header></Header>
    </el-affix>
    <el-row class="tac">
        <el-scrollbar max-height="calc(100vh - 50px)">
            <AsideMenu></AsideMenu>
        </el-scrollbar>
        <div style="flex: 1; overflow: hidden;">
            <el-scrollbar max-height="calc(100vh - 50px)">
                <router-view></router-view>
            </el-scrollbar>
        </div>
    </el-row>
</div>
</template>

<script>
    import Header from "@/components/Header";
    import AsideMenu from "@/components/AsideMenu";

    export default {
        name: "layout",
        components: {
            Header,
            AsideMenu
        }
    }
</script>

<style scoped>
    .AsideMenu {
        position: -webkit-sticky; /* Safari */
        position: sticky;
        top: 50px;
    }
</style>

<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)">
        <div>
            <p style="font-size: 14px; padding: 20px">
                文字占位
                页面展示
            </p>
        </div>
        <div style="margin: 10px 10px; width: 50%; display: flex; background-color: cornsilk">
            <el-input
                    v-model="search_snp"
                    class="w-50 m-2"
                    placeholder="Type something"
                    clearable
                    @keyup.enter="load"
            >
                <template #prefix>
                    <el-icon class="el-input__icon"><search /></el-icon>
                </template>
            </el-input>
            <el-button type="primary" style="margin-left: 5px" @click="load" >
                <el-icon class="el-input__icon"><search/></el-icon>
                Search
            </el-button>
        </div>
        <el-table
                :data="tableData"
                stripe
                style="max-width: 100%;"
                size="small"
                max-height="500"
                highlight-current-row
                @row-click="rowClick"
        >
            <el-table-column fixed prop="gene" label="Gene" sortable></el-table-column>
            <el-table-column prop="snp" label="SNP"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>
        <div>
            <p style="font-size: 14px; padding: 30px 0 0 20px">
                <b>{{geneName}}</b> 曼哈顿图
            </p>
        </div>
        <div v-if="mhtVisible">
            <el-image
                    style="width: 900px; height: 300px"
                    :src=imgUrl1
                    fit="cover">
            </el-image>
            <el-image
                    style="width: 300px; height: 300px"
                    :src=imgUrl2
                    fit="cover">
            </el-image>
        </div>
    </div>
</template>

<script>
    import { Search } from '@element-plus/icons-vue'
    import request from "../utils/request";

    export default {
        name: "eQTL",
        components:{
            Search
        },
        data(){
            return{
                search_snp:"",
                total:0,
                currentPage:1,
                pageSize:10,
                tableData : [],
                mhtVisible: false,
                geneName:null,
                imgUrl1: null,
                imgUrl2: null,
                // shuLog:
            }
        },
        created() {
            this.load()
            this.rowClick(this.tableData)
        },
        methods:{
            load(){
                request.get("/eqtl", {
                    params:{
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData = res.data.records
                    this.total = res.data.total
                })
            },
            handleSizeChange(pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load()
            },
            rowClick(row){
                this.mhtVisible = false
                this.geneName = null
                this.imgUrl1 = null
                this.imgUrl2 = null
                if (row.gene) {
                    this.mhtVisible = true
                    this.geneName = row.gene

                    this.imgUrl1 = require('@/assets/eqtl_mht_png/'+row.gene+"-"+row.snp.split(':')[0]+"-"+row.snp.split(':')[1]+'_manhattan.png')
                    this.imgUrl2 = require('@/assets/eqtl_mht_png/'+row.gene+'.txt.ps_qqplot.png')
                    // console.log(row)
                }
            }
        }
    }
</script>

<style scoped>

</style>

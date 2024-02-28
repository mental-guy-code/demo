<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)">
        <div>
            <p style="font-size: 14px; padding: 20px">
                emQTL: quantitative trait loci expressing methylation.
                The expression of methylation quantitative trait loci is essentially related to
                the methylation level and gene expression level. It is the first time to find the
                causative methylation sites of human breast cancer. Based on the correlation analysis
                strategy of linear regression, we analyzed the correlation between methylation sites and
                all Arabidopsis genes.
            </p>
        </div>
        <div style="margin: 10px 10px; width: 50%; display: flex; background-color: cornsilk">
            <el-input
                    v-model="search_snp"
                    class="w-50 m-2"
                    placeholder="Type something"
                    clearable
                    @keyup.enter="load1;load2"
            >
                <template #prefix>
                    <el-icon class="el-input__icon"><search /></el-icon>
                </template>
            </el-input>
            <el-button type="primary" style="margin-left: 5px" @click="load1;load2" >
                <el-icon class="el-input__icon"><search/></el-icon>
                Search
            </el-button>
        </div>
        <div>
            <h3 style="padding: 30px 0 0 20px">
                emQTL - <i>Gossypium hirsutum</i>
            </h3>
        </div>
        <el-table
                :data="tableData1"
                stripe
                style="max-width: 100%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="gene" label="Gene" sortable></el-table-column>
            <el-table-column prop="num" label="Num"></el-table-column>
            <el-table-column prop="smp" label="SMP"></el-table-column>
            <el-table-column prop="distance" label="Distance"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
            <el-table-column prop="slope" label="Slope"></el-table-column>
            <el-table-column
                    prop="meType"
                    label="Me_Type"
                    :filters="[
        { text: 'cg', value: 'cg' },
        { text: 'chg', value: 'chg' },
        { text: 'chh', value: 'chh' },
      ]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end"
            ></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage1"
                    v-model:page-size="pageSize1"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total1"
                    @size-change="handleSizeChange1"
                    @current-change="handleCurrentChange1"
            >
            </el-pagination>
        </div>
        <div>
            <h3 style="padding: 30px 0 0 20px">
                emQTL - <i>Arabidopsis thaliana</i>
            </h3>
        </div>
        <el-table
                :data="tableData2"
                stripe
                style="max-width: 100%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="gene" label="Gene" sortable></el-table-column>
            <el-table-column prop="num" label="Num"></el-table-column>
            <el-table-column prop="smp" label="SMP"></el-table-column>
            <el-table-column prop="distance" label="Distance"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
            <el-table-column prop="slope" label="Slope"></el-table-column>
            <el-table-column
                    prop="meType"
                    label="Me_Type"
                    :filters="[
        { text: 'cg', value: 'cg' },
        { text: 'chg', value: 'chg' },
        { text: 'chh', value: 'chh' },
      ]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end"
            ></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage2"
                    v-model:page-size="pageSize2"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total2"
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import { Search } from '@element-plus/icons-vue'
    import request from "../utils/request";

    export default {
        name: "emQTL",
        components:{
            Search
        },
        data(){
            return{
                search_snp:"",
                total1:0,
                currentPage1:1,
                pageSize1:10,
                tableData1 : [],
                total2:0,
                currentPage2:1,
                pageSize2:10,
                tableData2 : []
            }
        },
        created() {
            this.load1()
            this.load2()
        },
        methods:{
            load1(){
                request.get("/emqtlcotton", {
                    params:{
                        pageNum: this.currentPage1,
                        pageSize: this.pageSize1,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData1 = res.data.records
                    this.total1 = res.data.total
                })
            },
            handleSizeChange1 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load1()
            },
            handleCurrentChange1 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load1()
            },
            load2(){
                request.get("/emqtlat", {
                    params:{
                        pageNum: this.currentPage2,
                        pageSize: this.pageSize2,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData2 = res.data.records
                    this.total2 = res.data.total
                })
            },
            handleSizeChange2 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load2()
            },
            handleCurrentChange2 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load2()
            },
            filterTag (value, row){
                return row.meType === value
            }
        }
    }
</script>

<style scoped>

</style>

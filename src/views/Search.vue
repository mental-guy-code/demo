<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)">
        <div>
            <p style="font-size: 14px; padding: 20px">

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



        <div>
            <h3 style="padding: 30px 0 0 20px">
                GWAS
            </h3>
        </div>
        <el-table
                :data="tableData1"
                stripe
                style="max-width: 90%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="snp" label="SNP" sortable width="100"></el-table-column>
            <el-table-column prop="pvalue" label="P_value" width="100"></el-table-column>
            <el-table-column prop="maf" label="MAF" width="100"></el-table-column>
            <el-table-column prop="phenotype" label="Phenotype" width="100"></el-table-column>
            <el-table-column prop="reference" label="Reference" width="100"></el-table-column>
            <el-table-column prop="alteration" label="Alteration" width="100"></el-table-column>
            <el-table-column prop="gene" label="Gene" width="100"></el-table-column>
            <el-table-column prop="region" label="Region" width="100"></el-table-column>
            <el-table-column prop="arabId" label="ArabId" width="100"></el-table-column>
            <el-table-column prop="arabDesc" label="ArabDesc" width="300"></el-table-column>
            <el-table-column prop="fpkm" label="fpkm" width="50"></el-table-column>
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
                eQTL
            </h3>
        </div>
        <el-table
                :data="tableData2"
                stripe
                style="max-width: 90%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="gene" label="Gene" sortable></el-table-column>
            <el-table-column prop="snp" label="SNP"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
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
        <div>
            <h3 style="padding: 30px 0 0 20px">
                meQTL
            </h3>
        </div>
        <el-table
                :data="tableData3"
                stripe
                style="max-width: 90%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="smp" label="SMP" sortable></el-table-column>
            <el-table-column prop="num" label="Num"></el-table-column>
            <el-table-column prop="snp" label="SNP"></el-table-column>
            <el-table-column prop="distance" label="Distance"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
            <el-table-column prop="slope" label="Slope"></el-table-column>
            <el-table-column prop="meType" label="Me_Type"></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage3"
                    v-model:page-size="pageSize3"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total3"
                    @size-change="handleSizeChange3"
                    @current-change="handleCurrentChange3"
            >
            </el-pagination>
        </div>
        <div>
            <h3 style="padding: 30px 0 0 20px">
                emQTL - <i>Gossypium hirsutum</i>
            </h3>
        </div>
        <el-table
                :data="tableData4"
                stripe
                style="max-width: 90%;"
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
            <el-table-column prop="meType" label="Me_Type"></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage4"
                    v-model:page-size="pageSize4"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total4"
                    @size-change="handleSizeChange4"
                    @current-change="handleCurrentChange4"
            >
            </el-pagination>
        </div>
        <div>
            <h3 style="padding: 30px 0 0 20px">
                emQTL - <i>Arabidopsis thaliana</i>
            </h3>
        </div>
        <el-table
                :data="tableData5"
                stripe
                style="max-width: 90%;"
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
            <el-table-column prop="meType" label="Me_Type"></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage5"
                    v-model:page-size="pageSize5"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total5"
                    @size-change="handleSizeChange5"
                    @current-change="handleCurrentChange5"
            >
            </el-pagination>
        </div>
        <div>
            <h3 style="padding: 30px 0 0 20px">
                EWAS
            </h3>
        </div>
        <el-table
                :data="tableData6"
                stripe
                style="max-width: 90%;"
                size="small"
                max-height="500"
                highlight-current-row
        >
            <el-table-column fixed prop="smp" label="SMP" sortable></el-table-column>
            <el-table-column prop="phenotype" label="Phenotype"></el-table-column>
            <el-table-column prop="maf" label="Maf"></el-table-column>
            <el-table-column prop="pvalue" label="P_value"></el-table-column>
        </el-table>
        <div>
            <el-pagination
                    v-model:currentPage="currentPage6"
                    v-model:page-size="pageSize6"
                    :page-sizes="[10, 20, 100, 400]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total6"
                    @size-change="handleSizeChange6"
                    @current-change="handleCurrentChange6"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import { Search } from '@element-plus/icons-vue'
    import request from "../utils/request";

    export default {
        name: "search_web",
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
                tableData2 : [],

                total3:0,
                currentPage3:1,
                pageSize3:10,
                tableData3 : [],

                total4:0,
                currentPage4:1,
                pageSize4:10,
                tableData4 : [],

                total5:0,
                currentPage5:1,
                pageSize5:10,
                tableData5 : [],

                total6:0,
                currentPage6:1,
                pageSize6:10,
                tableData6 : []
            }
        },
        created() {
            this.load()
        },
        methods:{
            load(){
                request.get("/gwas", {
                    params:{
                        pageNum: this.currentPage1,
                        pageSize: this.pageSize1,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData1 = res.data.records
                    this.total1 = res.data.total
                })
                request.get("/eqtl", {
                    params:{
                        pageNum: this.currentPage2,
                        pageSize: this.pageSize2,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData2 = res.data.records
                    this.total2 = res.data.total
                })
                request.get("/meqtl", {
                    params:{
                        pageNum: this.currentPage3,
                        pageSize: this.pageSize3,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData3 = res.data.records
                    this.total3 = res.data.total
                })
                request.get("/emqtlcotton", {
                    params:{
                        pageNum: this.currentPage4,
                        pageSize: this.pageSize4,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData4 = res.data.records
                    this.total4 = res.data.total
                })
                request.get("/emqtlat", {
                    params:{
                        pageNum: this.currentPage5,
                        pageSize: this.pageSize5,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData5 = res.data.records
                    this.total5 = res.data.total
                })
                request.get("/ewas", {
                    params:{
                        pageNum: this.currentPage6,
                        pageSize: this.pageSize6,
                        search: this.search_snp
                    }
                }).then(res => {
                    this.tableData6 = res.data.records
                    this.total6 = res.data.total
                })
            },
            handleSizeChange1 (pageSize) {   //改变每页数量
                this.pageSize1 = pageSize
                this.load1()
            },
            handleCurrentChange1 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load1()
            },
            handleSizeChange2 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load2()
            },
            handleCurrentChange2 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load2()
            },
            handleSizeChange3 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load3()
            },
            handleCurrentChange3 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load3()
            },
            handleSizeChange4 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load4()
            },
            handleCurrentChange4 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load4()
            },
            handleSizeChange5 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load5()
            },
            handleCurrentChange5 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load5()
            },
            handleSizeChange6 (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load6()
            },
            handleCurrentChange6 (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load6()
            },
        }
    }
</script>

<style scoped>

</style>

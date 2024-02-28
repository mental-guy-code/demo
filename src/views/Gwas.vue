<template>
    <div style="padding: 30px; max-width: calc(100vw - 150px)">
<div>
    <p style="font-size: 14px; padding: 20px">
        GWAS: genome wide association analysis (GWAS) uses the linkage disequilibrium within a population
        to assess the degree of association between genomic variation and individual traits or diseases.
        GWAS has the characteristics of high throughput and low cost. It can detect the correlation between
        hundreds of millions of gene variants and traits or diseases in the whole genome. It has become one
        of the most important tools to find complex trait related genes or loci. Commonly used software
        includes Plink, Gemma and so on.
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
    </div>
</template>

<script>
    import { Search } from '@element-plus/icons-vue'
    import request from "../utils/request";

    export default {
        name: "Gwas",
        components:{
            Search
        },
        data(){
            return{
                search_snp:"",
                total:0,
                currentPage:1,
                pageSize:10,
                tableData : []
            }
        },
        created() {
            this.load()
        },
        methods:{
            load(){
                request.get("/gwas", {
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
            handleSizeChange (pageSize) {   //改变每页数量
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange (pageNum) {  //改变当前所在页
                this.pageNum = pageNum
                this.load()
            }
        }
    }
</script>

<style scoped>

</style>

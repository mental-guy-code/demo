(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0b1d7a"],{2226:function(e,t,a){"use strict";a.r(t);var n=a("7a23"),r={style:{padding:"30px","max-width":"calc(100vw - 150px)"}},o=Object(n["createElementVNode"])("div",null,[Object(n["createElementVNode"])("p",{style:{"font-size":"14px",padding:"20px"}}," 文字占位 页面展示 ")],-1),c={style:{margin:"10px 10px",width:"30%",display:"flex","background-color":"cornsilk"}},l=Object(n["createTextVNode"])(" Search ");function i(e,t,a,i,p,d){var b=Object(n["resolveComponent"])("search"),s=Object(n["resolveComponent"])("el-icon"),u=Object(n["resolveComponent"])("el-input"),h=Object(n["resolveComponent"])("el-button"),g=Object(n["resolveComponent"])("el-table-column"),j=Object(n["resolveComponent"])("el-table"),O=Object(n["resolveComponent"])("el-pagination");return Object(n["openBlock"])(),Object(n["createElementBlock"])("div",r,[o,Object(n["createElementVNode"])("div",c,[Object(n["createVNode"])(u,{modelValue:p.search_snp,"onUpdate:modelValue":t[0]||(t[0]=function(e){return p.search_snp=e}),class:"w-50 m-2",placeholder:"Type something",clearable:"",onKeyup:Object(n["withKeys"])(d.load,["enter"])},{prefix:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(s,{class:"el-input__icon"},{default:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(b)]})),_:1})]})),_:1},8,["modelValue","onKeyup"]),Object(n["createVNode"])(h,{type:"primary",style:{"margin-left":"5px"},onClick:d.load},{default:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(s,{class:"el-input__icon"},{default:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(b)]})),_:1}),l]})),_:1},8,["onClick"])]),Object(n["createVNode"])(j,{data:p.tableData,stripe:"",style:{width:"100%"},size:"small","max-height":"500","highlight-current-row":""},{default:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(g,{fixed:"",prop:"snp",label:"SNP",sortable:"",width:"100"}),Object(n["createVNode"])(g,{prop:"pvalue",label:"P_value",width:"100"}),Object(n["createVNode"])(g,{prop:"maf",label:"MAF",width:"100"}),Object(n["createVNode"])(g,{prop:"phenotype",label:"Phenotype",width:"100"}),Object(n["createVNode"])(g,{prop:"reference",label:"Reference",width:"100"}),Object(n["createVNode"])(g,{prop:"alteration",label:"Alteration",width:"100"}),Object(n["createVNode"])(g,{prop:"gene",label:"Gene",width:"100"}),Object(n["createVNode"])(g,{prop:"region",label:"Region",width:"100"}),Object(n["createVNode"])(g,{prop:"arabId",label:"ArabId",width:"100"}),Object(n["createVNode"])(g,{prop:"arabDesc",label:"ArabDesc",width:"300"}),Object(n["createVNode"])(g,{prop:"fpkm",label:"fpkm",width:"50"})]})),_:1},8,["data"]),Object(n["createElementVNode"])("div",null,[Object(n["createVNode"])(O,{currentPage:p.currentPage,"onUpdate:currentPage":t[1]||(t[1]=function(e){return p.currentPage=e}),"page-size":p.pageSize,"onUpdate:page-size":t[2]||(t[2]=function(e){return p.pageSize=e}),"page-sizes":[10,20,100,400],layout:"total, sizes, prev, pager, next, jumper",total:p.total,onSizeChange:d.handleSizeChange,onCurrentChange:d.handleCurrentChange},null,8,["currentPage","page-size","total","onSizeChange","onCurrentChange"])])])}var p=a("58f4"),d=a("b775"),b={name:"Gwas",components:{Search:p["a"]},data:function(){return{search_snp:"",total:0,currentPage:1,pageSize:10,tableData:[]}},created:function(){this.load()},methods:{load:function(){var e=this;d["a"].get("/gwas",{params:{pageNum:this.currentPage,pageSize:this.pageSize,search:this.search_snp}}).then((function(t){e.tableData=t.data.records,e.total=t.data.total}))},handleSizeChange:function(e){this.pageSize=e,this.load()},handleCurrentChange:function(e){this.pageNum=e,this.load()}}},s=a("6b0d"),u=a.n(s);const h=u()(b,[["render",i]]);t["default"]=h}}]);
//# sourceMappingURL=chunk-2d0b1d7a.f68bcdaa.js.map
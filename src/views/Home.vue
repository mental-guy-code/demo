<template>
  <div id="home">
      <div id="title"><h4>Welcome to Cotton DNA Methylation Database</h4></div>
      <div id="introduce">
          <div style="float:right ; clear:both ;" >
              <img src="@/assets/img_1.png">
          </div>
      <p>Multi omics integrated analysis refers to the systematic study of biological samples by combining at least
          two kinds of omics data (such as genome, transcriptome, proteome, metabolome or phenotypic group, etc.)
          to further explore and mine biological function information. Because biological phenomena are complex
          and changeable, gene expression regulation is diverse, and there are various modifications after gene
          transcription or translation, the study of single omics will inevitably lead to the loss of genetic
          information, resulting in incomplete conclusions. Therefore, the study of single omics has important
          defects. In recent years, intermediate molecular phenotypes (transcriptome, metabolites, etc.) have
          been widely used in the study of human disease multiomics. Multiomic association analysis has widely
          revealed the molecular mechanism of human disease formation.
      </p>
          <br>

      <p>
          At present, there are two main directions for the research of multi omics integration,
      </p>
          <br>
      <p>
          1) Develop efficient bioinformatics means to sort out, store, analyze and display multi omics data,
          so as to help researchers fully understand biological processes and genetic regulation at multiple levels;
      </p>
          <br>
      <p>
          2) Based on the idea of association and network, this paper studies the regulatory relationship between
          various omics from the perspective of system biology, and reveals the complex genetic and molecular
          mechanism from genome to phenotypic group.
      </p>
      </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {

  }
}
</script>

<style scoped>
    #home{
        padding: 20px;
    }

    #title{
        padding: 20px;
    }

    #introduce{
        padding: 20px;
    }
</style>

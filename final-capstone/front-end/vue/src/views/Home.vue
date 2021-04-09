<template>
  <div class="home">
           <h2> Tournaments </h2>
    <browse />
  </div>
</template>

<script>
import Browse from '@/components/Browse.vue'
import applicationServices from '@/services/ApplicationServices.js'

export default {
  name: "home",
  components: {
    Browse
  },
  
  methods: {
    getAllTournaments() {
      applicationServices.getTournaments().then((response) => {
        if (response.status === 201 || response.status === 200) {
          this.$store.commit("SET_TOURNAMENTS", response.data)
        }
      })
    }
  }
 
};
</script>
<style>
body {
  /* text-align: center; */
}


</style>

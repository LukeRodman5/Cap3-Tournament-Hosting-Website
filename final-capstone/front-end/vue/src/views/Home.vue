<template>
  <div class="home">
    <h1>Tournament Manager</h1>
    <p>Your home for hosting, finding and playing tournaments of all kinds</p>
    <browse/>
  </div>
</template>

<script>
import Browse from '@/components/Browse.vue'
export default {
  name: "home",
  components: {
  Browse
  }
 
};
</script>

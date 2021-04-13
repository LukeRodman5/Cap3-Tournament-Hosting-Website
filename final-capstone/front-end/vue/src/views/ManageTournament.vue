<template>
  <div>
      <h1>Manage Tournament</h1>
      <manage-tournament/>
      <add-user/>
  </div>
</template>

<script>
import ManageTournament from '@/components/ManageTournament.vue'
import AddUser from '../components/AddUser.vue'
export default {
    components:{
        ManageTournament,
        AddUser
    }
}
</script>

<style>

</style>
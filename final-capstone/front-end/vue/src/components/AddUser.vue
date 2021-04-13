<template>
<div>
  <h1>Send Invites</h1>
   <tr class="user-list" v-for="user in users" :key="user.username">
              <td class="name">{{user.username}}</td>
      </tr>
</div>
</template>

<script>
import applicationServices from '../services/ApplicationServices'
export default {
    name: "add-user",
    data(){
      return {
          users: [],
          user: {
              username: ""
          }
      }  
    },
    methods: {
        getUsers(){
        applicationServices.getAllUsers().then(response =>{
          this.$store.commit("SET_USERS", response.data)
          this.users=response.data
        })
        }
    },
    created() {
        this.getUsers();
    }

}

</script>

<style>

</style>
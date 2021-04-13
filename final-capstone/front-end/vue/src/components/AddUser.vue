<template>
<div>
  <h1>Send Invites </h1>
  <tbody>
   <tr class="user-list" v-for="user in this.users" :key="user.username">
              <td class="name">{{user.username}}</td>
      </tr>
  </tbody>
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
              userId: "",
              username: ""
          }
      }  
    },
    methods: {
        getUsers(){
            applicationServices.getAllUsers().then(response =>{
                this.users=response.data
            })//end of then
    }//end of get users
    },//end of methods
    created() {
        this.getUsers();
    }//end of created
    }//end of export

</script>

<style>

</style>
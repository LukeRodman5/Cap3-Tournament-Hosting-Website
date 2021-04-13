<template>
<div>
  <h1>Send Invites </h1>
  <tbody>
   <tr class="user-list" v-for="user in this.$store.state.users" :key="user.username">
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
        // getUsers(){
        // applicationServices.getAllUsers().then(response =>{
        //   this.users=response.data
        // })
        // },
            getUsers(){
            applicationServices.getAllUsers().then(response =>{
                this.users=response.data
            })
    },
    created() {
        this.getUsers();
    }
    }

}

</script>

<style>

</style>
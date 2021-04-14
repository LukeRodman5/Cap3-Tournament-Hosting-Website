<template>
<div class="invites">
 <form v-on:submit.prevent="inviteUser(currentTournament.tourneyId)">
  <h1>Send Invites </h1>
  <tbody>
   <tr class="user-list" v-for="user in this.users" :key="user.username">
        <td class="name">{{user.username}}</td>
        <input input type="checkbox" v-bind:id="user.username" v-bind:value="user.username" v-on:click="user.selected=true"/>
   </tr>    
  </tbody>
     <div>
       <button type="submit" class="btn save">Invite</button> 
   </div>
 </form>
</div>
</template>

<script>
import applicationServices from '../services/ApplicationServices'
export default {
    name: "add-user",
    data(){
      return {
          users: [],
          selectedUsers: [],
          user: {
              userId: "",
              username: "",
              selected: false,
              status: ""
          }
      }  
    },
    methods: {
      retrieveTournament() {
      applicationServices
        .getTournament(this.$route.params.tourneyID)
        .then(response => {
          this.currentTournament = response.data
              }
          )},//end of retreiveTournament 
        getUsers(){
            applicationServices.getAllUsers().then(response =>{
              this.users=response.data
            })//end of then
    },//end of get users

        inviteUsers(){//any user selected will be added to table with pending status
            selectedUsers.filter(user => user.selected === true)
            for(i=0; i<this.selectedUsers.length; i++){
                applicationServices.inviteUser(this.user.username, "pending").then(response =>{
                    this.user=response.data
                })
            }

        }
    },//end of methods
    created() {
        this.getUsers(),
        this.retrieveTournament()
    }//end of created
    }//end of export

</script>

<style>
form {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color:white;
    padding-top: 20px;
    padding-bottom: 40px;
    box-shadow: 0px 5px 5px rgb(112, 112, 112);
}
button{
    
}
tbody{
  text-align: center;
}
.start-date{
  text-align: center;
}
.end-date{
  text-align: center;
}
.participants{
  text-align: center;
}
.max-participants{
  text-align: center;
}

#tournament-host{

 box-shadow: 0px 5px 5px rgb(112, 112, 112);
}

#tournament-play{

 box-shadow: 0px 5px 5px rgb(112, 112, 112);
}
</style>
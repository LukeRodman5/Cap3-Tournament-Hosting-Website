<template>
    <form v-on: submit.prevent="addTournament">
     <div class="form-element">
        <label for="name">Tournament Name:</label>
        <input id="name" type="text" v-model="newTournament.name" />
    </div>
    <div class="form-element">
        <label for="description"> Tournament Description:</label>
        <input id="name" type="text" v-model="newTournament.description"/>
    </div>
    
    <div class ="form-element">
        <label for ="start-date"> Start Date:</label>
        <input id="start-date" type="date" v-model="newTournament.startDate"/>
    </div> 
    <div class ="form-element">
        <label for ="end-date"> End Date:</label>
        <input id="start-date" type="date" v-model="newTournament.endDate"/>
    </div> 
    <button type="submit" class="btn save">Save Tourament</button>
    </form>
</template>

<script>
export default {
    name: "add-tournament",
    data(){
        return{
            newTournament:{
                id: 0,
                name: '',
                description:'',
                host: this.$store.state.user.username,
                startDate:'',
                endDate:'',
                isActive:true
            },
            tournaments:[]
        }//end of return
    },//end of data
    methods:{
        addTournament(){
            applicationServices.addTournament(this.newTournament).then(response =>{
                if(response.status === 201){
                    this.getTournaments()
                    this.newTournament={
                        name: '',
                        description:'',
                        host: this.$store.state.user.username,
                        startDate:'',
                        endDate:'',
                        isActive:true}
                }//end of if
            })//end then
        },//end add tournament
        
    }


}
</script>

<style>

</style>

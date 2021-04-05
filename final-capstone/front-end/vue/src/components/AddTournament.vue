<template>
    <form>
        <!-- <div class "form-element">
            <label for ="name"> Name</label>
            <textarea id="name">Please enter the name of your tournament</textarea>
        </div> -->
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
                descrpition:'',
                host:'',
                startDate:'',
                endDate:'',
                isActive:true
            }
        }
    }


}
</script>

<style>

</style>

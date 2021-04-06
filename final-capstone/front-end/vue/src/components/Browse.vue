<template>
  <div>
    <table id="tblTournaments">
     <tbody>
      <tr>
       <th>&nbsp;</th>
       <th>Tournament Name</th>
       <th>Start Date</th>
       <th>End Date</th>
       <th>Description</th>
       <th>Host</th>
       <th>Status</th>
       <th>Join</th>
      </tr>
    
      <tr>
        <td>
          <input type="text" id="tournamentNameFilter"/>
        </td>
        <td>
          <input type="text" id="startDateFilter"/>
        </td>
        <td>
          <input type="text" id="endDateFilter"/>
        </td>
        <td>
          <input type="text" id="descriptionFilter"/>
        </td>
        <td>
          <input type="text" id="host"/>
        </td>
        <td>
          <select id="statusFilter">
          <option value> Show All </option>
          <option value = "Current">Current Events</option>
          <option value = "Upcoming">Upcoming Events</option>
          <option value = "Previous">Previous Events</option>
          </select>
        </td>
        <!-- This will direct to tournaments detail 
        <td>
          <button v-on:click="showTournament()"> 
        </td> -->
        <tr
            
        >


        <td>
          <input type="text" id="viewTournament"/>
        </td>
                        
      </tr>
     </tbody>
    </table>
 </div>
</template>

<script>
export default {
    name: "tournament-list",
    data() {
        return{
            allTournaments:[]
      
        }
    },
    methods: {

    }

}
</script>

<style>

</style>
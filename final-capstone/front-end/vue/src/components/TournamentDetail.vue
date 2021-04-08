<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img id="loadingImg" src="../assets/ping_pong_loader.gif" />
    </div>
    <div v-else>
      <h1>{{ currentTournament.tourneyName }}</h1>
      <p>{{ currentTournament.tourneyDesc }}</p>
      <p>{{ currentTournament.maxNumOfParticipants }}</p>
      <p> {{currentTournament.startDate}} | {{currentTournament.endDate}}</p>
      <button class="btnJoinTourney" v-on:click="joinTourney(currentTournament.tourneyId, this.$store.state.user.username)">Join Tournament</button>
      
      
    </div>

    <div class="board-actions" v-if="!isLoading">
      <router-link :to="{ name: 'home'}">Back to Tournaments</router-link>
    </div>
  </div>
</template>

<script>
import applicationServices from "../services/ApplicationServices";
//import CommentsList from "@/components/CommentsList"; - reserved for importing match list later

export default {
  name: "tournament-detail",
  components: {
    
    //match-list later
  },
  data() {
    return {
      isLoading: true,
      errorMsg: "",
      currentTournament: {}
    };
  },
  created() {
    this.retrieveTournament()
  },
  methods: {
    retrieveTournament() {
      applicationServices
        .getTournament(this.$route.params.tourneyID)
        .then(response => {
          //this.$store.commit("SET_CURRENT_TOURNAMENT", response.data);
          this.currentTournament = response.data
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert(
              "Card not available. This card may have been deleted or you have entered an invalid card ID."
            );
            this.$router.push("/");
          }
        });
    },
    deleteCard() {
      if (
        confirm(
          "Are you sure you want to delete this tournament? This action cannot be undone."
        )
      ) {
        applicationServices
          .deleteTournament(this.tournament.tourneyID)
          .then(response => {
            if (response.status === 200) {
              alert("Tournament successfully deleted");
              this.$router.push(`/`);
            }
          })
          .catch(error => {
            if (error.response) {
              this.errorMsg =
                "Error deleting tournament. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting tournament. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting tournament. Request could not be created.";
            }
          });
      }
    },//end of delete card
    joinTourney(tourneyId, userID){
      applicationServices.joinTourney(tourneyId, username)
    .then(response=>{
        if(response.status===200 || response.status===201){
          alert("You have successfully joined this tournament.")
        }//end of if
        else{
          alert("Attempt to join this tournament was unsuccessful.")
        }//end of else
        this.$router.push("/")
    } //end of then
    
    )}//end of flipJoin
  },//end of methods
  computed: {
    tournament() {
      return this.$store.state.tournament;
    }
  }
};
</script>

<style>
.btn.editTournament {
  color: #fff;
  background-color: #508ca8;
  border-color: #508ca8;
  margin-bottom: 10px;
}
#loadingImg {
  height: 200px;
  width:  200px;
}
.btn.deleteTournament {
  color: #fff;
  background-color: #ef031a;
  border-color: #ef031a;
  margin-bottom: 10px;
}
</style>
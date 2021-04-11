<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img id="loadingImg" src="../assets/ping_pong_loader.gif" />
    </div>
    <div v-else id="tourney-desc">
      <h1>{{ currentTournament.tourneyName }}</h1>
      <h2>Description</h2>
      <p>{{ currentTournament.tourneyDesc }}</p>
      <p>Max Number of Participants: {{ currentTournament.maxNumOfParticipants }}</p>
      <p> Start Date: {{currentTournament.startDate}} | End Date: {{currentTournament.endDate}}</p>
      <button class="btnJoinTourney" v-on:click="joinTourney(currentTournament.tourneyId, $store.state.user.username)">Join Tournament</button>
    </div>

    <div v-if="!isLoading">
      <router-link :to="{ name: 'home'}">Back to Tournaments</router-link>
    </div>
    <div id="bracket">
      <bracket :rounds="rounds">
        <template slot="player" slot-scope="{ player }">
            {{ player.name }}
          </template>
      </bracket>
    </div>
  </div>
</template>

<script>
import applicationServices from "../services/ApplicationServices";
import Bracket from "vue-tournament-bracket";

const tbdPlayers = { name: "TBD", winner: null, score: 0 }

export default {
  name: "tournament-detail",
  components: {
    Bracket
  },
  data() {
    return {
      isLoading: true,
      errorMsg: "",
      currentTournament: {},
      usersInTourney: [],
      rounds: []
//       rounds: [
//         //Semi finals
//     {
//         games: [
//             {
//                 player1: { id: "1", name: "Competitor 1", winner: null, score: 0 },
//                 player2: { id: "4", name: "Competitor 4", winner: null, score: 0 },
//             },
//             {
//                 player1: { id: "5", name: "Competitor 4", winner: null, score: 0 },
//                 player2: { id: "8", name: "Competitor 8", winner: null, score: 0 },
//             }
//         ]
//     },
//     //Final
//     {
//         games: [
//             {
//                 player1: tbdPlayers,
//                 player2: tbdPlayers,
//             }
//         ]
//     }
// ]
    };
  },
  created() {
    this.retrieveTournament()
    this.getUsersInTourney()
  },
  methods: {
    retrieveTournament() {
      applicationServices
        .getTournament(this.$route.params.tourneyID)
        .then(response => {
          this.currentTournament = response.data
          this.isLoading = false;
        })
    },
    deleteTournament() {
      if (
        confirm(
          "Are you sure you want to delete this tournament? This action cannot be undone."
        )
      ) {
        applicationServices
          .deleteTournament(this.currentTournament.tourneyID)
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
    joinTourney(tourneyId, username){
      applicationServices.joinTourney(tourneyId, username)
      .then(response=>{
        if(response.status===200 || response.status===201){
          alert("You have successfully joined this tournament")
          // this.currentTournament.numOfParticipants = this.currentTournament.numOfParticipants ++
          // if(this.currentTournament.numOfParticipants >= this.currentTournament.maxNumOfParticipants){
          //   this.currentTournament.openForReg = false
          // }
          // applicationServices.updateTournament(this.currentTournament, tourneyId)
          // .then(response=>{
          //   if(response.status === 200 || response.status===201){
          //     alert("You have successfully joined this tournament")
          //   }
          // })
        }//end of if
        else{
          alert("Attempt to join this tournament was unsuccessful.")
        }//end of else
      this.$router.push("/")
      }) //end of then
    
    },//end of joinTourney
    getUsersInTourney() {
      applicationServices.getUsersInTourney(this.$route.params.tourneyID).then((response) => {
        if (response.status === 200 || response.status === 201) {
          this.usersInTourney = response.data
          this.setUpBracket()
        } else {
          console.log("Error")
        }
        
      })
    },
    setUpBracket() {
      let gamesHolder = {games: []}
      let match = {}
      this.usersInTourney.forEach((user) => {
       let player = {id: user.userId, name: user.username, winner: null, score: 0}
        if (!('player1' in match)) {
          match.player1 = player
        } else {
          match.player2 = player
          gamesHolder.games.push(match)
          match = {}
        }
      })
      this.rounds.push(gamesHolder)
      gamesHolder = {games: []}
      match = {player1: tbdPlayers, player2: tbdPlayers}
      gamesHolder.games.push(match)
      match = {}
      this.rounds.push(gamesHolder)
    }
  }//end of methods
};
</script>

<style>
#bracket {
  display: flex;
  justify-content: center;
}
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
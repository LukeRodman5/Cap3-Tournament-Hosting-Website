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
      <!-- <generate-tournament v-if="isHost" v-bind:currentTourney=currentTournament /> -->
    </div>

    <!-- <div v-if="!isLoading">
      <router-link :to="{ name: 'home'}">Back to Tournaments</router-link>
    </div> -->
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
// import GenerateTournament from './GenerateTournament.vue';

const tbdPlayers = { name: "TBD", winner: null }

export default {
  name: "tournament-detail",
  components: {
    Bracket,
    // GenerateTournament
  },
  data() {
    return {
      isHost: false,
      isLoading: true,
      errorMsg: "",
      currentTournament: {},
      usersInTourney: [],
      matchesInTourney: [],
      rounds: []
    };
  },
  created() {
    this.retrieveTournament()
  },
  methods: {
    retrieveTournament() {
      applicationServices.getTournament(this.$route.params.tourneyID).then(response => {
        this.currentTournament = response.data
        this.isLoading = false
        this.isHost = this.currentTournament.tourneyHost === this.$store.state.user.username
        this.getMatchesInTourney(this.$route.params.tourneyID)
        if (this.matchesInTourney.length === 0) {
          this.getUsersInTourney()
        }
      })
    },
    deleteTournament() {
      if (confirm("Are you sure you want to delete this tournament? This action cannot be undone.")) {
        applicationServices.deleteTournament(this.currentTournament.tourneyID).then(response => {
          if (response.status === 200) {
            alert("Tournament successfully deleted");
            this.$router.push(`/`);
          }
        }).catch(error => {
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
        })
      }
    },//end of delete card
    joinTourney(tourneyId, username){
      applicationServices.joinTourney(tourneyId, username, 'Approved')
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
          this.setUpIniBracket()
        } else {
          console.log("Error")
        }
      })
    },
    setUpIniBracket() {
      // let matchToAddDb = {}
      
      // let gamesHolder = {games: []}
      // let match = {}
      // let matchCount = 0;
      // this.usersInTourney.forEach((user) => {
      //   let player = {id: user.userId, name: user.username, winner: null}
        
      //   if (!('player1' in match)) {
      //     match.player1 = player
      //   } else {
      //     match.player2 = player
      //     gamesHolder.games.push(match)
      //     match = {}
      //     matchCount++
      //   }
      // })
      // if (('player1' in match) && !('player2' in match)) {
      //   match.player2 = tbdPlayers
      //   gamesHolder.games.push(match)
      //   match = {}
      //   matchCount++
      // }
      let matchesCount = this.currentTournament.maxNumOfParticipants / 2
      let roundCount = 0
      let match = {}

      for (let i = 0; i < matchesCount; i++) {
        match = {startDate: this.currentTournament.startDate, startTime: null, roundLevel: roundCount}
        applicationServices.createMatch(match).then((response) => {
          // Created match response
          if (response.status === 200 || response.status === 201) {
            // maybe add to current tournament? applicationServices
          } else {
            console.log("Create match error")
          }
        })
        if (i === matchesCount - 1 && matchesCount > 1) {
          i = -1
          matchesCount = matchesCount / 2
          roundCount++
        }
      }

      // let iniRoundsCount = this.currentTournament.maxNumOfParticipants / 2
      // for (let i = matchCount; i < iniRoundsCount; i++) {
      //   match = {player1: tbdPlayers, player2: tbdPlayers}
      //   gamesHolder.games.push(match)
      //   match = {}
      //   if (i === iniRoundsCount - 1) {
      //     this.rounds.push(gamesHolder)
      //     gamesHolder = {games: []}
      //     if (iniRoundsCount >= 1) {
      //       i = -1
      //       iniRoundsCount = iniRoundsCount / 2
      //     }
      //   }
      // }
    },
    getMatchesInTourney(tourneyId) {
      applicationServices.getMatchesInTourney(tourneyId).then((response) => {
        if (response.status === 200 || response.status === 201) {
          this.matchesInTourney = response.data
        }
      })
    }
  }//end of methods
};
</script>

<style scoped>
#bracket {
  display: flex;
  justify-content: center;
}
#tourney-desc, #tourney-desc>h2 {
  text-align: center;
}
#tourney-desc {
  margin-bottom: 30px;
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
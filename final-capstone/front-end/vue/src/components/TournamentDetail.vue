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
      <!-- buttons below will be displayed as JOIN TOURNAMENT for all users, EDIT TOURNAMENT for tourney host
       this functionality will be added later-->
      <!-- <router-link
        tag="button"
        :to="{ name: 'EditCard', params: {cardID: $route.params.cardID} }"
        class="btn editCard"
      >Edit Card</router-link>
      <button class="btn deleteCard" v-on:click="deleteCard">Delete Card</button>
      <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
      <comments-list :comments="card.comments" /> //this will be match list once we build it-->
    </div>

    <div class="board-actions" v-if="!isLoading">
      <router-link :to="{ name: 'browse',  }">Back to Tournaments</router-link>
    </div>
  </div>
</template>

<script>
import applicationServices from "../services/ApplicationServices";
//import Browse from "../components/Browse"
//import CommentsList from "@/components/CommentsList"; - reserved for importing match list later

export default {
  name: "tournament-detail",
  components: {
    //Browse,
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
    },
  },
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
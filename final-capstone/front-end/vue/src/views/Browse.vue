 <template>
    <div>
        <h1>Browse</h1>
        <!-- <browse/> -->
        <tournament-list/>
    </div>
</template>
 
 <script>
import Browse from '@/components/Browse.vue'
import TournamentList from '../components/TournamentList.vue'
export default {
  components: { 
      //Browse
      TournamentList
      } 
}
</script>
 
 <style>
 
 </style>
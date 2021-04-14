 <template>
    <div>
        <h1 class="MyTournaments">My Tournaments</h1>
        <my-tournaments/>
    </div>
</template>
 
 <script>
import MyTournaments from '../components/MyTournaments.vue'
export default {
  components: { 
    MyTournaments
    } 
}
</script>
 
 <style>
 .MyTournaments{
   text-align: right;
   padding-top: 10px;
   font-size: 30pt;
   padding-right: 30px;
   background-color: white;
 }
 
 </style>
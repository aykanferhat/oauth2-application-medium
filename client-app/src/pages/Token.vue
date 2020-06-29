<script>

  import {mapActions} from 'vuex';
  import router from '../routes'

  export default {
    name: 'token',
    async created() {
      this.getToken()
    },
    methods: {
      ...mapActions('user', ["getTokenWithCode"]),
      async getToken() {

        await this.getTokenWithCode(this.$route.query.code)
        router.push({name: 'profile'});
      }
    }
  }

</script>

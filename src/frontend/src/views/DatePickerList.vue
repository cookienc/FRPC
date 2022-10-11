<template>
  <v-form>
    <v-container>
      <input type="date" id="input_date" :max="getToday()" :value="getToday()">
      <v-btn style="margin-left: 30px" @click="goto()">조회
      </v-btn>
    </v-container>
  </v-form>
</template>

<script>
export default {
  data() {
    return {
    }
  },

  methods: {
    getToday() {
      var today = new Date();
      var year = today.getFullYear();
      var month = ("0" + (today.getMonth() + 1)).slice(-2);
      var day = ("0" + today.getDate()).slice(-2);
      var dateString = year + "-" + month + "-" + day;
      return dateString;
    },
    goto() {
      var date = document.querySelector("#input_date").value;
      date = date.concat(" 00:00:00");
      this.$router.push({name: 'list', query: {date: date}});
    }
  }
}
</script>
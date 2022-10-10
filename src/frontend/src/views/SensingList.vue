<template>
  <h1>List</h1>
  <div v-if="records.length >= 1">
    <v-table>
      <thead>
      <th>번호</th>
      <th>날짜</th>
      </thead>
      <tbody
          :key="i" v-for="(record, i) in records">
      <td>{{ i + 1 }}</td>
      <td>{{ makeDate(record.time) }}</td>
      <td>
        <v-btn @click="goto(record.pressureId, record.flexId)"></v-btn>
      </td>
      </tbody>
    </v-table>
  </div>
  <div id="err" v-else>
    기록이 없습니다
  </div>
  <datePicker/>
</template>

<script>
import axios from "axios";
import datePicker from "./DatePickerList"

export default {

  components: {
    datePicker: datePicker,
  },

  data() {
    return {
      records: [],
    }
  },

  created() {
    this.getData();
  },

  methods: {
    makeDate(date) {
      var ret = date.substring(0, 10) + " "
          + date.substring(11, 13) + "시 "
          + date.substring(14, 16) + "분 "
          + date.substring(17, 20) + "초"
      return ret;
    },

    getDate() {
      return this.$route.query.date;
    },

    async getData() {
      await axios
          .get("http://localhost:8080/home/list", {
            params: {
              date: this.getDate(),
            },
          })
          .then((res) => {
            console.log(res.data);
            this.records = res.data;
          })
          .catch((err) => console.log(err));
    },

    goto(pressureId, flexId) {
      this.$router.push({
        name: 'barGraph', query: {
          pressureId: pressureId,
          flexId: flexId,
        }
      });
    },
  },
}
</script>
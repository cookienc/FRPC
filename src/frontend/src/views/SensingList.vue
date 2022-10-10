<template>
  <h1>[{{ makeTitle(this.$route.query.date) }}]</h1>
  <div class="flex" v-if="records.length >= 1">
    <v-table id="table">
      <thead>
        <th>번호</th>
        <th>날짜</th>
        <th>상세 보기</th>
      </thead>
      <tbody
          :key="i" v-for="(record, i) in records">
      <td>{{ i + 1 }}</td>
      <td>{{ makeDateAndTime(record.time) }}</td>
      <td>
        <v-btn color="#A1887F"
               elevation="2"
               @click="goto(record.pressureId, record.flexId)">이동
        </v-btn>
      </td>
      </tbody>
    </v-table>
  </div>
  <div class="flex" id="err" v-else>
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
    makeTitle(date) {
      var ret = date.substring(0, 4) + "년 " +
          date.substring(5, 7) + "월 " +
          date.substring(8, 10) + "일" +
          "의 기록"
      return ret;
    },

    makeDateAndTime(date) {
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
          .get("http://localhost:8080/api/list", {
            params: {
              date: this.getDate(),
            },
          })
          .then((res) => {
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

<style>
h1 {
  margin-top: 50px;
}

#table {
  margin-top: 30px;
  margin-bottom: 30px;
  border: 1px solid black;
  border-collapse: collapse;
  width: 100%;
  max-width: 1000px;
}

th {
  background: whitesmoke;
}

th,
td {
  border: 1px solid black;
  padding: 20px;
  padding-left: 40px;
  padding-right: 40px;
}

.flex {
  display: grid;
  place-content: center;
  margin-bottom: 50px;
}

#err {
  margin-top: 30px;
  margin-bottom: 30px;
  font-size: x-large;
}
</style>
<template>
  <div id="app">
    <h1>최근 5일 동안 곡률반 측정 현황</h1>
    <div v-if="getFlexes().length > 1">
      <GChart
      id="chart"
      type="LineChart"
      :data="getFlexes()"
      :options="chartOptions"
    />
    </div>
    <div id="err" v-else>
      최근 5일간의 기록이 없습니다
    </div>
    <date-picker/>
  </div>
</template>

<script>
import axios from "axios";
import { GChart } from "vue-google-charts";
import datePicker from "./DatePickerFlexLine"

export default {
  name: "App",
  components: {
    GChart,
    datePicker: datePicker
  },

  data() {
    return {
      pressures: [],

      chartOptions: {
        hAxis: {
          title: "날짜",
          titleTextStyle: { color: "#333" },
        },
        vAxis: {
          title: "곡률반경(cm)",
        },
      },
    };
  },

  created() {
    this.getData();
  },

  methods: {
    getDate() {
      return this.$route.query.date;
    },

    async getData() {
      var url = process.env.VUE_APP_API_URI + "/api/line-graph/flex";
      console.log(url)
      await axios
        .get(url, {
          params: {
            date: this.getDate(),
          },
        })
        .then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.pressures.push(res.data[i]);
          }
        })
        .catch((err) => console.log(err));
    },

    getFlexes() {
      var list = [["날짜",  "검지", "중지", "약지"]];

      for (var i in this.pressures) {
        var tmp = [];
        tmp.push(this.pressures[i].date.substr(0, 10));
        tmp.push(this.pressures[i].indexFinger);
        tmp.push(this.pressures[i].middleFinger);
        tmp.push(this.pressures[i].ringFinger);
        list.push(tmp);
      }
      return list;
    },
  },
};
</script>

<style>
h1 {
  margin-top: 50px;
}

#err {
  margin-top: 30px;
  font-size: x-large;
}

#chart {
  height: 700px;
  margin-bottom: 30px;
}
</style>
<template>
  <div id="app">
    <h1>최근 5일간 곡률반경 측정 현황</h1>
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
      flexes: [],

      chartOptions: {
        hAxis: {
          title: "날짜",
          titleTextStyle: { color: "#333" },
        },
        vAxis: {
          title: "곡률반경(cm)",
          minValue: 1.5,
          viewWindow: {
            min: 1.5,
          }
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
            this.flexes.push(res.data[i]);
          }
        })
        .catch((err) => console.log(err));
    },

    getFlexes() {
      var list = [["날짜",  "검지", "중지", "약지"]];

      for (var i in this.flexes) {
        var tmp = [];
        tmp.push(this.flexes[i].date.substr(0, 10));
        tmp.push(this.flexes[i].indexFinger);
        tmp.push(this.flexes[i].middleFinger);
        tmp.push(this.flexes[i].ringFinger);
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
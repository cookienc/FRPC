<template>
  <div id="app">
    <h1>손가락 압력 그래프</h1>
    <GChart
      id="chart"
      type="BarChart"
      :settings="{ packages: ['corechart'] }"
      :data="getChartInfo()"
      :options="chartOptions"
    />
    <div class="flex">
      <table id="table">
        <caption>
          [구부림 정도]
        </caption>
        <thead>
          <tr>
<!--            <th class="text-left">엄지</th>-->
            <th class="text-left">검지</th>
            <th class="text-left">중지</th>
            <th class="text-left">약지</th>
<!--            <th class="text-left">새끼</th>-->
          </tr>
        </thead>
        <tbody>
<!--          <td>{{ flexes.thumb }}</td>-->
          <td>{{ flexes.indexFinger }}</td>
          <td>{{ flexes.middleFinger }}</td>
          <td>{{ flexes.ringFinger }}</td>
<!--          <td>{{ flexes.littleFinger }}</td>-->
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { GChart } from "vue-google-charts";

export default {
  name: "App",

  components: {
    GChart,
  },

  data() {
    return {
      flexes: [],
      pressures: [],

      chartOptions: {
        hAxis: {
          title: "손가락",
          titleTextStyle: { color: "#333" },
        },
        vAxis: {
          title: "압력",
          minValue: 0,
          ticks: [0, 5, 10, 15, 20, 25, 30, 35, 40, 45],
        },
      },
    };
  },

  created() {
    return this.getData();
  },

  methods: {
    getChartInfo() {
      return [
        ["손가락", "사용자"],
        ["엄지", this.pressures.thumb],
        ["검지", this.pressures.indexFinger],
        ["중지", this.pressures.middleFinger],
        ["약지", this.pressures.ringFinger],
        ["새끼", this.pressures.thumb],
      ];
    },
    async getData() {
      var url = process.env.VUE_APP_API_URI + "/api/bar-graph";
      console.log(url);
      await axios
        .get(url, {
          params: {
            pressureId: this.$route.query.pressureId,
            flexId: this.$route.query.flexId,
          },
        })
        .then((res) => {
          console.log(res.data)
          this.flexes = res.data.flexes;
          this.pressures = res.data.pressures;
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>
<style>
#chart {
  border: 5px;
  height: 700px;
}

#table {
  border: 1px solid black;
  border-collapse: collapse;
  width: 100%;
  max-width: 800px;
}
tr {
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

caption {
  font-size: x-large;
  margin-bottom: 10px;
}
</style>
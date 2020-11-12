import React from "react";
import ReactApexChart from "react-apexcharts"

let jdata = require('./csvjson.json');

export default class Graph extends React.Component {
  constructor(props){
    super(props);
    this.state={
        series:[{data:[]}],
        options: {
            chart: {
                height: 50,
                type: 'candlestick',
                animations: {
                    enabled:false
                },
                zoom:{
                    type:'xy'
                }
            },
            title: {
              text: 'Graph  ',
              align: 'center',
              style: {
                fontSize:  '32px',
                fontWeight:  'bold',
                color:  '#263238'
              },
            },
            theme: { 
                monochrome: {
                    enabled: false,
                    color: '#255aee',
                    shadeTo: 'light',
                    shadeIntensity: 0.65
                },
            },
            plotOptions: {
              candlestick: {
                wick: {
                  useFillColor: true,
                }
              },
            },
            tooltip: {
              enabled: true,
            },
            xaxis: {
              type: 'category',
              labels: {
                format: 'dd/MM',
              }
            },
            yaxis: {
              tooltip: {
                enabled: true
              }
            }
        },
    };
    
}
  set1hr =()=>{
      var temp=[];
      this.setState({
        series:[{data:[]}]
      })
      for(let i = 0 ; i < jdata.length ;i++){
        let tempdate = jdata[i].date;
        tempdate = tempdate.split(".");
        let temphr = jdata[i].hr;
        temphr = temphr.split(".");
        let date = new Date();
        date.setHours(parseInt(temphr[0].split(":")[0]));
        date.setMinutes(parseInt(temphr[0].split(":")[1]));
        date.setMonth(parseInt(tempdate[1]-1));
        date.setFullYear(parseInt(tempdate[0]));
        date.setDate(parseInt(tempdate[2])); 
        let tempall = {x:date,y:[jdata[i].open,jdata[i].high,jdata[i].low,jdata[i].close]};
        temp.push(tempall);
      }
      this.setState({
        series:[{data:temp}]
      })
  }
  set6hr =()=>{
    var temp=[];
    this.setState({
      series:[{data:[]}]
    })
    for(let i = 0 ; i < jdata.length ;i++){
      let tempdate = jdata[i].date;
      tempdate = tempdate.split(".");
      let temphr = jdata[i].hr;
      temphr = temphr.split(".");
      let date = new Date();
      date.setHours(parseInt(temphr[0].split(":")[0]));
      date.setMinutes(parseInt(temphr[0].split(":")[1]));
      date.setMonth(parseInt(tempdate[1]-1));
      date.setFullYear(parseInt(tempdate[0]));
      date.setDate(parseInt(tempdate[2])); 
      if(i % 6 == 0){
        let close = jdata[i].close;
      }
      let tempall = {x:date,y:[jdata[i].open,jdata[i].high,jdata[i].low,jdata[i].close]};
      temp.push(tempall);
    }
    this.setState({
      series:[{data:temp}]
    })
}


  render() {
    return (
<div>
<button onClick={this.set1hr}>1Hr</button>
<ReactApexChart 
options={this.state.options} 
series={this.state.series} type="candlestick" height={350} />
</div>
    );
  }
}

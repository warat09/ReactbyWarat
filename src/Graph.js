import React from "react";
import ReactApexChart from "react-apexcharts"
import * as dayjs from 'dayjs';

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
                    enabled:true
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
                colors: {
                  upward: '#00e600',
                  downward: '#ff6600'
                }
              }
            },
            tooltip: {
              enabled: true,
            },
            xaxis: {
              type: 'category',
              labels: {
                formatter: function(val) {
                  return dayjs(val).format('MMM DD HH:mm')
                }
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
  set1hr=()=>{
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
        date.setFullYear(parseInt(tempdate[0]));
        date.setMonth(parseInt(tempdate[1]-1));
        date.setDate(parseInt(tempdate[2])); 
        let tempall = {x:date,y:[jdata[i].open,jdata[i].high,jdata[i].low,jdata[i].close]};
        temp.push(tempall);
      }
      this.setState({
        series:[{data:temp}]
      })
  }
  set6hr=()=>{
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
      
      if(i != 0){
        if(i %6 == 0){
          let tempall = {x:date,y:[open,maxhigh,minlow,jdata[i-1].close]};
          temp.push(tempall);
          open = jdata[i].open;
          minlow = jdata[i].low;
          maxhigh = jdata[i].high;
        }
        
        else{
          if(jdata[i].high > maxhigh){
            maxhigh = jdata[i].high;
          }
          if(jdata[i].low < minlow){
            minlow = jdata[i].low;
          }
          
        }
      }
      else if(i == 0){
        var open = jdata[0].open;
        var maxhigh = jdata[0].high;
        var minlow = jdata[0].low;
      }
      
    }
    this.setState({
      series:[{data:temp}]
    })
}
set24hr =()=>{
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
    
    if(i != 0){
      if(i %24 == 0){
        let tempall = {x:date,y:[open,maxhigh,minlow,jdata[i-1].close]};
        temp.push(tempall);
        open = jdata[i].open;
        minlow = jdata[i].low;
        maxhigh = jdata[i].high;
      }
      
      else{
        if(jdata[i].high > maxhigh){
          maxhigh = jdata[i].high;
        }
        if(jdata[i].low < minlow){
          minlow = jdata[i].low;
        }
        
      }
    }
    else if(i == 0){
      var open = jdata[0].open;
      var maxhigh = jdata[0].high;
      var minlow = jdata[0].low;
    }
    
  }
  this.setState({
    series:[{data:temp}]
  })
}
set3day=()=>{
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
    
    if(i != 0){
      if(i %72 == 0){
        let tempall = {x:date,y:[open,maxhigh,minlow,jdata[i-1].close]};
        temp.push(tempall);
        open = jdata[i].open;
        minlow = jdata[i].low;
        maxhigh = jdata[i].high;
      }
      
      else{
        if(jdata[i].high > maxhigh){
          maxhigh = jdata[i].high;
        }
        if(jdata[i].low < minlow){
          minlow = jdata[i].low;
        }
        
      }
    }
    else if(i == 0){
      var open = jdata[0].open;
      var maxhigh = jdata[0].high;
      var minlow = jdata[0].low;
    }
    
  }
  this.setState({
    series:[{data:temp}]
  })
}
set1week =()=>{
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
    
    if(i != 0){
      if(i % 168 == 0){
        let tempall = {x:date,y:[open,maxhigh,minlow,jdata[i-1].close]};
        temp.push(tempall);
        open = jdata[i].open;
        minlow = jdata[i].low;
        maxhigh = jdata[i].high;
      }
      
      else{
        if(jdata[i].high > maxhigh){
          maxhigh = jdata[i].high;
        }
        if(jdata[i].low < minlow){
          minlow = jdata[i].low;
        }
        
      }
    }
    else if(i == 0){
      var open = jdata[0].open;
      var maxhigh = jdata[0].high;
      var minlow = jdata[0].low;
    }
    
  }
  this.setState({
    series:[{data:temp}]
  })
}
  render() {
    return (
<div>
<ReactApexChart 
options={this.state.options} 
series={this.state.series} type="candlestick" height={350} />
<button onClick={this.set1hr}>1HR</button><button onClick={this.set6hr}>6HR</button><button onClick={this.set24hr}>24HR</button><button onClick={this.set3day}>3days</button><button onClick={this.set1week}>7days</button>

</div>
    );
  }
}

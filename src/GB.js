import './App.css';
import React, { Component } from 'react';
import SortableTree from 'react-sortable-tree';
import 'react-sortable-tree/style.css'; // This only needs to be imported once in your app
import axios from 'axios'

export default class Tree extends Component {
  constructor(props) {
    super(props);
 
    this.state = {
      treeData: [],
      image:""
    };
  }
  show(){
    if(this.state.image != "notpic"){
      return(
        <div>
          <img src={"data:image/png;base64,"+this.state.image}style ={{height:500}}/>
        </div>
      )

    }
  }
  componentWillMount(){
    axios.get('http://localhost:9000/testAPI').then((res)=>this.setState({treeData:res.data}))
  }
  render() {
    return (
      <div style={{ height: 400 }}>
        <h1>100GB</h1>
        <SortableTree
          treeData={this.state.treeData}
          onChange={treeData => this.setState({ treeData })}
          generateNodeProps={selectnode=>({
            onClick:()=>axios({
              method:'get',
              url:'http://localhost:9000/testAPI/image',
              params:{
                path:selectnode.node.path
              }
            }).then((res)=>{
              this.setState({image:res.data})
            })
          })
          }
        />
        {this.show()}
      </div>
    );
  }
}


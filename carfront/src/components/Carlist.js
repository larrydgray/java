import React, { Component } from 'react';
//import {SERVER_URL} from '../constants.js';
import ReactTable from 'react-table-6';
import 'react-table-6/react-table.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import AddCar from './AddCar.js';
import { CSVLink } from 'react-csv';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';


class Carlist extends Component{


    constructor(props){
        super(props)
        this.state = { cars: [] };
    }
    
    componentDidMount(){
        this.fetchCars();
    }

    fetchCars=()=>{
        const token = sessionStorage.getItem("jwt");
        fetch('/api/cars',
        {
            headers:{'Authorization':token}
        })
        .then((response) => response.json())
        .then((responseData)=> {
            this.setState({
                cars: responseData._embedded.cars,
            });
        })
        .catch(err =>{
            
            console.error(err);
        })
    }

    onDelClick=(link)=>{
        const token = sessionStorage.getItem("jwt");
        link=link.substring(21, link.length);
        console.log('onDelClick link '+link)
        fetch(link, {
            method: 'DELETE',
            headers: {'Authorization': token}
            }
        )
        .then(res => {
            this.setState({open: true, message: 'Car deleted'});
            this.fetchCars();
        })
        .catch(err=>{
            this.setState({open: true, message: 'Error when deleting'});
            console.error(err)
        })
    }

    confirmDelete = (link) => {
        confirmAlert({
            message: 'Are you srue to delete?',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => this.onDelClick(link)
                },
                {
                    label: 'No',
                }
            ]
        })
    }

    addCar(car){
        const token = sessionStorage.getItem("jwt");
        console.log('Adding car '+ JSON.stringify(car))
        fetch('/api/cars',
            {method: 'POST',
                headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
                },
                body: JSON.stringify(car)

            })
            .then( res => this.fetchCars())
            .catch( err => console.error(err))
        }

    renderEditable = (cellInfo) => {
        return (
            <div 
                style={{ backgroundColor: "#fafafa" }}
                contentEditable
                suppressContentEditableWarning
                onBlur={e => {
                    const data = [...this.state.cars];
                    data[cellInfo.index][cellInfo.column.id] = 
                     e.target.innerHTML;
                    this.setState({cars: data});

                }}
                dangerouslySetInnerHTML={{
                    __html: this.state.cars[cellInfo.index][cellInfo.column.id]
                }}
            />
        );
    }
    
    updateCar(car, link){
        const token = sessionStorage.getItem("jwt");
        link=link.substring(21, link.length);
        fetch(link,
        { method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            body: JSON.stringify(car)
        })
        .then( res=>
           this.setState({open: true, message: 'Changes saved'})
        )
        .catch( err => 
            this.setState({open: true, message: 'Error when saving'})
        )
            
    }

    handleClose = (event, reason) => {
        this.setState({ open: false });
    };


    render(){
        const columns = [{
            Header: 'Brand',
            accessor: 'brand',
            Cell: this.renderEditable
        },{
            Header: 'Model',
            accessor: 'model',
            Cell: this.renderEditable
        },{
            Header: 'Color',
            accessor: 'color',
            Cell: this.renderEditable
        },{
            Header: 'Year',
            accessor: 'year',
            Cell: this.renderEditable
        },{
            Header: 'Price $',
            accessor: 'price',
            Cell: this.renderEditable
        },{
            id: 'savebutton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: '_links.self.href',
            Cell: ({value, row}) =>
            (<button onClick={() => {this.updateCar(row,value)}}>
                    Save</button>)
        },{
            id: 'delbutton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: '_links.self.href',
            Cell: ({value})=> (<button onClick={()=>{this.confirmDelete(value)}}>Delete</button>)
        }]

        return(
            <div className="App">
                Car Table
                <Grid container>
                    <Grid item>
                        <AddCar addCar={this.addCar} fetchCars={this.fetchCars}/>
                    </Grid>
                    <Grid item>
                        <CSVLink data={this.state.cars} separator=";">Export CSV </CSVLink>
                    </Grid>
                </Grid>
                <ReactTable data={this.state.cars} columns={columns} filterable={true}/>
                <Snackbar 
                    style = {{width: 300, color: 'green'}}
                    open={this.state.open} onClose={this.handleClose}
                    autoHideDuration={1500} message={this.state.message}/>
            </div>
        );
    }
}

export default Carlist;
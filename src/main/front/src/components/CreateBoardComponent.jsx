import React, { Component } from 'react'
import BoardService from '../service/BoardService'

export default class CreateBoardComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            title: '',
            content: '',
            writer: '',
        }

        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeContentHandler = this.changeContentHandler.bind(this);
        this.changeWriterHandler = this.changeWriterHandler.bind(this);
        this.createBoard = this.createBoard.bind(this);
    }

    changeTitleHandler = (event) => {
        this.setState({title: event.target.value});
    }

    changeContentHandler = (event) => {
        this.setState({content: event.target.value});
    }

    changeWriterHandler = (event) => {
        this.setState({writer: event.target.value});
    }

    createBoard = (event) => {
        event.preventDefault();
        let board = {
            title: this.state.title,
            content: this.state.content,
            writer: this.state.writer
        };
        console.log("board => " + JSON.stringify(board));
        BoardService.createBoard(board).then(res => {
            this.props.history.push('/board');
        });
    }

    cancel() {
        this.props.history.pysh('/board');
    }


    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 className='text-center'>새글을 작성해주세요</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label>Title</label>
                                        <input type="text" placeholder='title' name='title' className='form-control' value={this.state.title} onChange={this.changeTitleHandler} />
                                    </div>
                                    <div className='form-group'>
                                        <label>Content</label>
                                        <input type="content" placeholder='content' name='title' className='form-control' value={this.state.content} onChange={this.changeContentHandler} />
                                    </div>
                                    <div className='form-group'>
                                        <label>Writer</label>
                                        <input type="writer" placeholder='writer' name='writer' className='form-control' value={this.state.writer} onChange={this.changeWriterHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.createBoard}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            
            </div>
        )
    }
}

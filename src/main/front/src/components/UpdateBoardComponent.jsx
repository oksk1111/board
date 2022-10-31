import React, { Component } from 'react'
import BoardService from '../service/BoardService'

export default class UpdateBoardComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id, 
            title: '',
            content: '',
            writer: '',
        }

        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeContentHandler = this.changeContentHandler.bind(this);
        this.changeWriterHandler = this.changeWriterHandler.bind(this);
        this.updateBoard = this.updateBoard.bind(this);
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

    updateBoard = (event) => {
        event.preventDefault();
        let board = {
            id: this.state.id,
            title: this.state.title,
            content: this.state.content,
            writer: this.state.writer
        };
        console.log("board => " + JSON.stringify(board));

        BoardService.updateBoard(this.state.id, board).then(res => {
            this.props.history.push('/board');
        });

    }


    cancel() {
        this.props.history.push('/board');
    }


    componentDidMount() {
        BoardService.getOneBoard(this.state.id).then( (res) => {
            let board = res.data;
            console.log("board => "+ JSON.stringify(board));
            
            this.setState({
                id: board.id,
                title: board.title,
                content: board.content,
                writer: board.writer
            });
        });
    }


    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 className="text-center">{this.state.id}글을 수정 합니다.</h3>
                            <div className='card-body'>
                                <form>
                                    <input type="hidden" name="id" value={this.state.id} />
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
                                    <button className="btn btn-success" onClick={this.updateBoard}>Save</button>
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

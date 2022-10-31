import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = { 
            boards: []
        }
		
        this.createBoard = this.createBoard.bind(this);
    }


    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }


    createBoard() {
        this.props.history.push('/create-board/_create');
    }


    readBoard(id) {
        this.props.history.push(`/read-board/${id}`);
    }


    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.createBoard}> 글 작성</button>
                </div>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>수정일 </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key={board.id}>
                                        <td> {board.id} </td>
                                        <td><a onClick = {() => this.readBoard(board.id)}> {board.title} </a></td>
                                        <td> {board.writer} </td>
                                        <td> {board.createdDate} </td>
                                        <td> {board.modifiedDate} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ListBoardComponent;
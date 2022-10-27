import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)
    
        this.state = { 
            boards: []
        }
		
    }
    

    componentDidMount() {
        BoardService.getBoards().then((res) => {
            this.setState({ boards: res.data});
        });
    }

    
    render() {
        return (
            <div>
                <h2 className="text-center">Boards List</h2>
                <div className ="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
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
                                    <tr key = {board.id}>
                                        <td> {board.id} </td>
                                        <td> {board.title} </td>
                                        <td> {board.writter} </td>
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
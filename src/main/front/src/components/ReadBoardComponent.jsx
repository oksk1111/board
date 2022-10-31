import React, { Component } from 'react';
import BoardService from '../service/BoardService';

export default class ReadBoardComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            board: {}
        }
    }


    componentDidMount() {
        BoardService.getOneBoard(this.state.id).then(res => {
            this.setState({board: res.data});
        });
    }


    returnDate(cTime, uTime) {
        return (
            <div className='row'>
                <label>생성일: [{cTime}] / 최종 수정일: [{uTime}]</label>
            </div>
        )
    }

    
    goToList() {
        this.props.history.push('/board');
    }


    goToUpdate = (event) => {
        event.preventDefault();
        this.props.history.push(`/update-board/${this.state.id}`);
    }


    deleteView = async function() {
        if (window.confirm("정말로 글을 삭제하겠습니까?")) {
            BoardService.deleteBoard(this.state.id).then(res => {
                console.log("delete result => " + JSON.stringify(res));
                
                if (res.status == 200) {
                    this.props.history.push('/board');
                } else {
                    alert("글 삭제를 실패했습니다.");
                }

            });
                
            
        }
    }


    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center">Read Detail</h3>
                    <div className="card-body">
                        <div className="row">
                            <label>Title</label>: {this.state.board.title}
                        </div>
                        <div className="row">
                            <label>Contents</label>: <br></br>
                            <textarea value={this.state.board.content} readOnly />
                        </div>
                        <div className="row">
                            <label>Writer</label>: {this.state.board.writer}
                        </div>

                        {this.returnDate(this.state.board.createdDate, this.state.board.modifiedDate)}
                        <button className="btn btn-primary" onClick={this.goToList.bind(this)} style={{marginLeft:'10px'}}>글 목록으로 이동</button>
                        <button className="btn btn-info" onClick={this.goToUpdate} style={{marginLeft:"10px"}}>글 수정</button>
                        <button className="btn btn-danger" onClick={() => this.deleteView()} style={{marginLeft:"10px"}}>글 삭제</button>
                    </div>
                </div>
            </div>
        )
    }
}

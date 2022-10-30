import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api"; 

class BoardService {

    getBoards() {
        return axios.get(BOARD_API_BASE_URL + "/board");
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL + "/post", board);
    }

    getOneBoard(id) {
        return axios.get(BOARD_API_BASE_URL + "/board/" + id);
    }
}

export default new BoardService();
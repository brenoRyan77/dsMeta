import icon from '../../assets/img/notification-icon.svg'
import './style.css'
import axios from 'axios';
import { BASE_URL } from '../../utils/request';

type Props = {
    saleId: number;
}

function handleClick(id : number){
    axios.get(`${BASE_URL}/sales/${id}/notificacao`)
    .then().catch(error => {
        console.log(error)
    });
}

function NotificationButton({saleId} : Props) {
    return (
        <div className="dsmeta-red-btn" onClick={() => {handleClick(saleId)}}>
            <img src={icon} alt="Notificar" />
        </div>
    )
}

export default NotificationButton

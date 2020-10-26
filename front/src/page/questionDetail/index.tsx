import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import axios, {AxiosError} from 'axios';
import {QuestionDetailVo} from "../../entity/QuestionDetailVo";
import {ErrorResponse} from "../../entity/ErrorResponse";
import {Button, message} from "antd";
import QuestionView from "./QuestionView";
import AnswerView from "./AnswerView";
import {Wrapper} from "./style";
import WriteAnswerForm from "./WriteAnswerForm";

const QuestionDetail = () => {
    const {id} = useParams();
    const [formHidden, setFormHidden] = useState(true)
    const [questionDetail, setQuestionDetail] = useState<QuestionDetailVo>()
    console.log(id)
    useEffect(() => {
        const loadQuesion = async () => {
            try {
                const response = await axios.get<QuestionDetailVo>(`/api/question/id/${id}`)
                setQuestionDetail(response.data)
            } catch (e) {
                const ex: AxiosError<ErrorResponse> = e
                message.warn(ex.response?.data.message)
            }
        }

        loadQuesion()
    }, [id]);
    if (questionDetail == null) {
        return <div>加载中</div>
    }
    return (
        <Wrapper>
            <QuestionView question={questionDetail.question}/>
            <Button style={{marginBottom: 20}} type="primary"
                    onClick={() => setFormHidden(false)}>写回答</Button>
            <WriteAnswerForm
                hidden={formHidden}
                setHidden={setFormHidden}
                questionId={id}
            />
            {questionDetail.answers.map((item) => <AnswerView answer={item} key={item.id}/>)}
        </Wrapper>
    )
}

export default QuestionDetail;

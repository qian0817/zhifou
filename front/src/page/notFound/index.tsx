import {Button, Result} from "antd";
import React from "react";
import {Helmet} from "react-helmet";
import {useHistory} from "react-router";

const NotFound = () => {
    const history = useHistory()
    return (
        <>
            <Helmet title="您访问的页面不存在"/>
            <Result
                status="404"
                title="404"
                subTitle="您访问的页面不存在"
                extra={<Button type="primary" onClick={() => history.push("/")}>回到主页</Button>}
            />
        </>
    )
}

export default NotFound
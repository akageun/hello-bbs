export default function ({store, redirect, error}) {
  console.log("test : ", store.state.accessToken);
  if (store.state.accessToken === undefined || store.state.accessToken === null) {
    console.log("test :afsasd ");
    return redirect('/login')
    // error({
    //   message: 'You are not connected',
    //   statusCode: 403
    // })

  }
}

import React from 'react';
function AddUserPage() {
    return (
      <div>
        <form id="post-form" method="post" action="http://localhost:3001/user/add">
        <input autocomplete="off" type="text" name="id"/>
        <input autocomplete="off" type="text" name="name"/>
        <input autocomplete="off" type="text" name="email"/>
        <input autocomplete="off" type="text" name="phonenum"/>
        <input autocomplete="off" type="text" name="address"/>
        <input autocomplete="off" type="text" name="who"/>
        <input autocomplete="off" type="text" name="password"/>
        <input type="submit"/>
      </form>
      </div>
    );
  }
export default AddUserPage;
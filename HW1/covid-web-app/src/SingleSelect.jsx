import React from 'react'
import Select from 'react-select'

const SingleSelect = ({id,options, onChange}) => {

  return <Select 
    id={id}
    options={options}
    isSearchable
    isClearable
    onChange={onChange}
  />
}

export default SingleSelect;
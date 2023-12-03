import { C8 } from 'camunda-8-sdk'
import { config } from 'dotenv'

config()

const con = new C8.ConsoleApiClient()

async function main() {
    console.log('----------con.getParameters------------')
    const params = await con.getParameters()
    console.log(JSON.stringify(params, null, 2))
    con.createCluster

    console.log('----------zbc.topology------------')

    const zbc = new C8.ZBClient()
    const topology = await zbc.topology()
    console.log(JSON.stringify(topology, null, 2))
}

main()
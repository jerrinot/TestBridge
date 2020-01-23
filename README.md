# TestBridge
Shows how to use Hazelcast3 bridge for Jet4

There are 2 Maven modules:
- hz3server - Starts Hazelcast IMDG v3.x server and a producer inserting data into IMap
- demo - It shows how Jet 4.x can read from IMDG 3.x IMap

Known issues: 
- Discovery SPI to be tested. The bridge uses class relocation and I suspect this will not play nicelyh with the SPI. 

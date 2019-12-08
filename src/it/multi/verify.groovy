
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

File buildinfoFile = new File( basedir, "target/buildinfo" );

assert buildinfoFile.isFile()

File multi = new File( basedir, "../../local-repo/org/apache/maven/plugins/it/multi/1.0-SNAPSHOT/multi-1.0-SNAPSHOT.buildinfo")

assert multi.isFile()

File modA = new File( basedir, "../../local-repo/org/apache/maven/plugins/it/multi-modA/1.0-SNAPSHOT/multi-modA-1.0-SNAPSHOT.buildinfo")

assert modA.isFile()

File modB = new File( basedir, "../../local-repo/org/apache/maven/plugins/it/multi-modB/1.0-SNAPSHOT/multi-modB-1.0-SNAPSHOT.buildinfo")

assert modB.isFile()

File aggregate = new File( basedir, "../../local-repo/org/apache/maven/plugins/it/multi-modB/1.0-SNAPSHOT/multi-modB-1.0-SNAPSHOT-aggregate.buildinfo")

assert modB.isFile()

String buildinfo = multi.text
assert buildinfo.contains( "mvn.rebuild-args=-DskipTests verify" )
assert buildinfo.contains( "mvn.aggregate-buildinfo=org.apache.maven.plugins.it:multi-modB:1.0-SNAPSHOT" )

buildinfo = modA.text
assert buildinfo.contains( "mvn.build-root=org.apache.maven.plugins.it:multi:1.0-SNAPSHOT" )
assert buildinfo.contains( "outputs.0.filename=multi-modA-1.0-SNAPSHOT.jar" )

buildinfo = modB.text
assert buildinfo.contains( "mvn.build-root=org.apache.maven.plugins.it:multi:1.0-SNAPSHOT" )
assert buildinfo.contains( "outputs.0.filename=multi-modB-1.0-SNAPSHOT.jar" )

buildinfo = aggregate.text
assert buildinfo.contains( "mvn.rebuild-args=-DskipTests verify" )
assert buildinfo.contains( "mvn.build-root=org.apache.maven.plugins.it:multi:1.0-SNAPSHOT" )
assert buildinfo.contains( "outputs.0.filename=multi-modA-1.0-SNAPSHOT.jar" )
assert buildinfo.contains( "mvn.build-root=org.apache.maven.plugins.it:multi:1.0-SNAPSHOT" )
assert buildinfo.contains( "outputs.0.filename=multi-modB-1.0-SNAPSHOT.jar" )

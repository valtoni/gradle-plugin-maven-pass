package info.boaventura.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin

import org.sonatype.plexus.components.cipher.DefaultPlexusCipher
import org.sonatype.plexus.components.sec.dispatcher.DefaultSecDispatcher
import org.sonatype.plexus.components.sec.dispatcher.SecUtil
import groovy.xml.StreamingMarkupBuilder 
import groovy.xml.XmlUtil

class MavenCredentialsPlugin implements Plugin<Project> {

  def dc = new DefaultPlexusCipher()
  def dd = new DefaultSecDispatcher()
  def maven_home = System.getProperty( "user.home" ) + "/.m2/"

  void apply(Project project) {
    def mvncredentials = project.extensions.create("mvncredentials", MavenCredentialsPluginExtension)
    //println "${project.greeting.message} from ${project.greeting.greeter}"
    
    // Delegando o cipher
    dd.setConfigurationFile(mvncredentials.configurationFile)
    dd._cipher = dc      
    
    def maven_settings_file = new File(maven_home + 'settings.xml')
    def records = new XmlSlurper(false, false).parse(maven_settings_file)
    def servers = records.'**'.findAll{ node-> node.name() == 'server' }
    
    // TODO Make this flexible to put any String from this
    mvncredentials.username = servers[0].username
    mvncredentials.password = dd.decrypt(servers[0].password.text())
    mvncredentials.easteregg = 'This is something special for you'
  }
}

class MavenCredentialsPluginExtension {
  String username
  String password
  String easteregg
  // Path default to looking for settings security (In DefaultSecDispatcher)
  String configurationFile = "~/.m2/settings-security.xml"
}
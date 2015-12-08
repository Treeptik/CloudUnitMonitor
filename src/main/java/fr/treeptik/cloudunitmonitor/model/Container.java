/*
 * LICENCE : CloudUnit is available under the GNU Affero General Public License : https://gnu.org/licenses/agpl.html
 *     but CloudUnit is licensed too under a standard commercial license.
 *     Please contact our sales team if you would like to discuss the specifics of our Enterprise license.
 *     If you are not sure whether the GPL is right for you,
 *     you can always test our software under the GPL and inspect the source code before you contact us
 *     about purchasing a commercial license.
 *
 *     LEGAL TERMS : "CloudUnit" is a registered trademark of Treeptik and can't be used to endorse
 *     or promote products derived from this project without prior written permission from Treeptik.
 *     Products or services derived from this software may not be called "CloudUnit"
 *     nor may "Treeptik" or similar confusing terms appear in their names without prior written permission.
 *     For any questions, contact us : contact@treeptik.fr
 */

package fr.treeptik.cloudunitmonitor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@MappedSuperclass
public class Container
    implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Integer id;

    @Temporal( TemporalType.TIMESTAMP )
    protected Date startDate;

    protected String name;

    protected String containerID;

    @Transient
    protected String containerFullId;

    protected Long memorySize;

    protected String containerIP;

    /**
     * This is the docker state of the container containing the server : Running, Ghost, or Stopped
     */
    protected String dockerState;

    /**
     * This is the applicative status of the server START, STOP, PENDING when it's being modified and not yet in
     * operational state or FAIL if a problem has been detected on this server.
     */
    @Enumerated( EnumType.STRING )
    protected Status status;

    @ManyToOne( fetch = FetchType.LAZY )
    protected Image image;

    /**
     * for docker use
     */
    @ElementCollection
    @LazyCollection( LazyCollectionOption.FALSE )
    protected Map<String, String> listPorts = new HashMap<>();

    @ManyToOne
    protected Application application;

    protected String internalDNSName;

    @Transient
    protected Map<String, String> volumes;

    @Transient
    protected List<String> volumesFrom;

    private String sshPort;

    @OneToMany( cascade = CascadeType.REMOVE )
    private Set<PortToOpen> portsToOpen;

    public Container()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate( Date startDate )
    {
        this.startDate = startDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getContainerID()
    {
        return containerID;
    }

    public void setContainerID( String containerID )
    {
        this.containerID = containerID;
    }

    public String getContainerFullID()
    {
        return containerFullId;
    }

    public void setContainerFullID( String containerFullId )
    {
        this.containerFullId = containerFullId;
    }

    public Long getMemorySize()
    {
        return memorySize;
    }

    public void setMemorySize( Long memorySize )
    {
        this.memorySize = memorySize;
    }

    public String getContainerIP()
    {
        return containerIP;
    }

    public void setContainerIP( String containerIP )
    {
        this.containerIP = containerIP;
    }

    public String getDockerState()
    {
        return dockerState;
    }

    public void setDockerState( String dockerState )
    {
        this.dockerState = dockerState;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage( Image image )
    {
        this.image = image;
    }

    public Map<String, String> getListPorts()
    {
        return listPorts;
    }

    public void setListPorts( Map<String, String> listPorts )
    {
        this.listPorts = listPorts;
    }

    public Application getApplication()
    {
        return application;
    }

    public void setApplication( Application application )
    {
        this.application = application;
    }

    public String getSshPort()
    {
        return sshPort;
    }

    public void setSshPort( String sshPort )
    {
        this.sshPort = sshPort;
    }

    public String getInternalDNSName()
    {
        return internalDNSName;
    }

    public void setInternalDNSName( String internalDNSName )
    {
        this.internalDNSName = internalDNSName;
    }

    public Map<String, String> getVolumes()
    {
        return volumes;
    }

    public void setVolumes( Map<String, String> volumes )
    {
        this.volumes = volumes;
    }

    public List<String> getVolumesFrom()
    {
        return volumesFrom;
    }

    public void setVolumesFrom( List<String> volumesFrom )
    {
        this.volumesFrom = volumesFrom;
    }

    public List<PortToOpen> getPortsToOpen()
    {
        if ( portsToOpen != null )
        {
            return new ArrayList<>( portsToOpen );
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public void setPortsToOpen( List<PortToOpen> portsToOpen )
    {
        this.portsToOpen = new HashSet<>( portsToOpen );
    }

}

def call(image, tag, repo, branch) {
withCredentials([usernamePassword(
                 credentialsId: 'github-token',
                 usernameVariable: 'GIT_USER',
                 passwordVariable: 'GIT_TOKEN')]) 
{
    sh """
        rm -rf manifests
        git clone -b ${branch} ${repo} manifests
        cd manifests

        sed -i 's|image:.*|image: ${image}:${tag}|' deployment.yaml

        git config user.email "imaisalama@gmail.com"
        git config user.name "MaiSalama"

        git add .
        git commit -m "Update image to ${tag}"
        git push origin ${branch}
    """
}
}

